package com.example.todo.tasks
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context

data class TasksModel(
    var tasksCod: Int,
    var tasksName: String,
    var tasksDuration: String,
    var tasksIsSelected: Int
)

class TasksViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasksViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class TasksViewModel(context: Context) : ViewModel(), ViewModelProvider.Factory {

    private val _tasksListMarked = MutableStateFlow<List<TasksModel>>(emptyList())
    private val _tasksListNotMarked = MutableStateFlow<List<TasksModel>>(emptyList())
    private val _tasksList = MutableStateFlow<List<TasksModel>>(emptyList())
    val tasksList: StateFlow<List<TasksModel>> = _tasksList.asStateFlow()
    val tasksListNotMarked: StateFlow<List<TasksModel>> = _tasksListNotMarked.asStateFlow()
    val tasksListMarked: StateFlow<List<TasksModel>> = _tasksListMarked.asStateFlow()
    private val dbHandler: DBHandler = DBHandler(context)

    init {
        viewModelScope.launch {

            // Load data from the database and update the StateFlow

            _tasksList.value = dbHandler.readTasks() ?: emptyList()
            _tasksListNotMarked.value = dbHandler.readTasksNotSelected() ?: emptyList()

        }
    }

    fun addNewTask(newTaskName: String, newTaskDuration: String) {
        viewModelScope.launch {
            val newTask = TasksModel(

                tasksCod = _tasksList.value.size+1,
                tasksName = newTaskName,
                tasksDuration = newTaskDuration,
                tasksIsSelected = 0 // Default value, you can modify this as needed
            )

            // Update the StateFlow with the new task
            _tasksList.value = _tasksList.value + newTask

            _tasksListNotMarked.value = _tasksListNotMarked.value + newTask



            // Add the new task to the database
            dbHandler.addNewTasks(newTaskName, newTaskDuration)
        }
    }
    fun updateTasks(tasksModel: TasksModel) {
        viewModelScope.launch {
            // Update the StateFlow with the updated task
            _tasksList.value = _tasksList.value.map {
                if (it.tasksCod == tasksModel.tasksCod) tasksModel else it
            }

            _tasksListMarked.value = _tasksListMarked.value.mapNotNull {
                if (it.tasksCod == tasksModel.tasksCod && tasksModel.tasksIsSelected == 0) null else it
            }
            _tasksListNotMarked.value = _tasksListNotMarked.value.mapNotNull {
                if (it.tasksCod == tasksModel.tasksCod && tasksModel.tasksIsSelected == 1) null else it
            }
            if (tasksModel.tasksIsSelected==1) _tasksListMarked.value += tasksModel else _tasksListNotMarked.value += tasksModel
            // Update the task in the database
            dbHandler.updateTasks(
                tasksModel.tasksCod,
                tasksModel.tasksName,
                tasksModel.tasksDuration,
                tasksModel.tasksIsSelected
            )
        }
    }
    fun updateTasksRemove(tasksModel: TasksModel) {
        viewModelScope.launch {



            // Update the task in the database
            dbHandler.updateTasks(
                tasksModel.tasksCod,
                tasksModel.tasksName,
                tasksModel.tasksDuration,
                tasksModel.tasksIsSelected
            )

            // Update the StateFlow
            _tasksList.value = _tasksList.value.map {
                if (it.tasksCod == tasksModel.tasksCod && tasksModel.tasksIsSelected == 1) {
                    null // Exclude this item
                } else {
                    it // Keep the item in the list
                }
            }.filterNotNull()
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModelFactory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return this as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
