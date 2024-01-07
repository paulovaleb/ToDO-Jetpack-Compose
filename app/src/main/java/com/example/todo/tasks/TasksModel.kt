package com.example.todo.tasks
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context
<<<<<<< HEAD
import android.util.Log
=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83

data class TasksModel(
    var tasksCod: Int,
    var tasksName: String,
    var tasksDuration: String,
    var tasksIsSelected: Int
)

<<<<<<< HEAD
=======

>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
class TasksViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TasksViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

<<<<<<< HEAD
class TasksViewModel(context: Context) : ViewModel(), ViewModelProvider.Factory {

    private val _tasksListMarked = MutableStateFlow<List<TasksModel>>(emptyList())
    private val _tasksListNotMarked = MutableStateFlow<List<TasksModel>>(emptyList())
    private val _tasksList = MutableStateFlow<List<TasksModel>>(emptyList())
    val tasksList: StateFlow<List<TasksModel>> = _tasksList.asStateFlow()
    val tasksListNotMarked: StateFlow<List<TasksModel>> = _tasksListNotMarked.asStateFlow()
    val tasksListMarked: StateFlow<List<TasksModel>> = _tasksListMarked.asStateFlow()
=======

class TasksViewModel(context: Context) : ViewModel(), ViewModelProvider.Factory {

    private val _tasksList = MutableStateFlow<List<TasksModel>>(emptyList())
    val tasksList: StateFlow<List<TasksModel>> = _tasksList.asStateFlow()
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    private val dbHandler: DBHandler = DBHandler(context)

    init {
        viewModelScope.launch {

            // Load data from the database and update the StateFlow
<<<<<<< HEAD
            _tasksList.value = dbHandler.readTasks() ?: emptyList()
            _tasksListNotMarked.value = dbHandler.readTasksNotSelected() ?: emptyList()
=======
            _tasksList.value = dbHandler.readTaskss() ?: emptyList()
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
        }
    }

    fun addNewTask(newTaskName: String, newTaskDuration: String) {
        viewModelScope.launch {
            val newTask = TasksModel(
<<<<<<< HEAD
                tasksCod = _tasksList.value.size+1,
=======
                tasksCod = _tasksList.value.size /* Assign a unique ID, you can use a timestamp or generate it as needed */,
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
                tasksName = newTaskName,
                tasksDuration = newTaskDuration,
                tasksIsSelected = 0 // Default value, you can modify this as needed
            )

            // Update the StateFlow with the new task
            _tasksList.value = _tasksList.value + newTask
<<<<<<< HEAD
            _tasksListNotMarked.value = _tasksListNotMarked.value + newTask
=======

>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
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
<<<<<<< HEAD
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
=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83

            // Update the task in the database
            dbHandler.updateTasks(
                tasksModel.tasksCod,
                tasksModel.tasksName,
                tasksModel.tasksDuration,
                tasksModel.tasksIsSelected
            )
<<<<<<< HEAD
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
=======
        }
    }

>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModelFactory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return this as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
