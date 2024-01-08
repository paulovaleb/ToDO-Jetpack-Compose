package com.example.todo.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import java.util.Date
import com.example.todo.tasks.ui.theme.ToDOTheme

class Tasks : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TasksViewModel by viewModels { TasksViewModelFactory(this) }
            var pop by remember { mutableStateOf(false) }

            ToDOTheme {
                Column (modifier = Modifier.fillMaxHeight().blur(if (pop) 15.dp else 0.dp)){

                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    Text("Tasks")
                                }
                            )
                            TasksDrawer(viewModel)
                            Box {
                                FloatingActionButton(
                                    modifier = Modifier
                                        .padding(all = 16.dp)
                                        .align(alignment = Alignment.BottomEnd),
                                    onClick = { pop = true },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = "Add"
                                        )
                                    })
                                if (pop) if (AddTask(viewModel)) pop = false
                            }
                }
        }
    }
    }
        @Composable
        fun TasksDrawer(viewModel: TasksViewModel) {
            val tasksListNotMarked by viewModel.tasksListNotMarked.collectAsState()
            val tasksListMarked by viewModel.tasksListMarked.collectAsState()
            val (list2Open, setList2Open) = remember { mutableStateOf(true) }

            LazyColumn(
                modifier = Modifier
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth()
            ) {
                itemsIndexed(tasksListNotMarked) { index, _ ->
                    LayoutCardTask(tasksListNotMarked[index], viewModel)
                }
                if (tasksListMarked.isNotEmpty()) {
                    // List 2 header with toggle button
                    item {
                        Row(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .clickable { setList2Open(!list2Open) }
                        ) {
                            Text(
                                text = "Finished",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = if (list2Open) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = null, tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    // List 2 items (conditionally based on whether the list is open)
                    if (list2Open) {
                        itemsIndexed(tasksListMarked) { index, _ ->
                            LayoutCardTask(tasksListMarked[index], viewModel)
                        }
                    }
                }
            }
        }

    @Composable
    fun CircularCheckbox(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
    ) {
        Box(
            modifier = Modifier
                .clickable { onCheckedChange(!checked) }
                .size(24.dp)
                .background(

                    color = if (checked) Color.Green else Color.Transparent,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = CircleShape
                )
                .padding(4.dp) // Optional padding for visual aesthetics
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

        @Composable
        fun LayoutCardTask(task: TasksModel, viewModel: TasksViewModel) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                var a: Int
                var isChecked = task.tasksIsSelected == 1

                CircularCheckbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        a = if (isChecked) 1 else 0
                        viewModel.updateTasks(
                            TasksModel(
                                tasksCod = task.tasksCod,
                                tasksName = task.tasksName,
                                tasksDuration = task.tasksDuration,
                                tasksIsSelected = a
                            )
                        )
                    }
                )
                TaskViewFields(task)
            }
        }



        @Composable
        fun TaskViewFields(task: TasksModel) {
            Column {
                Text(
                    text = task.tasksName,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Start,
                    color = Color.White // Set text color to white
                )

                Spacer(modifier = Modifier.width(5.dp))
                if (task.tasksDuration != "null") {
                    Text(
                        text = task.tasksDuration,
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Start,
                        color = Color.White // Set text color to white
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
        }


                @Composable
                fun AddTask(viewModel: TasksViewModel): Boolean {
                        var pop by remember { mutableStateOf(false) }
                        var boxPosition by remember { mutableStateOf(DpOffset(5.dp, 0.dp)) }
                        BackHandler {
                            pop = true
                        }
                    Card (){
                        Column(modifier = Modifier
                            .padding(all = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            pop = TaskInputFields(viewModel)
                        }
                    }

                        return pop
                    }


                    @OptIn(ExperimentalMaterial3Api::class)
                    @Composable
                    fun TaskInputFields(viewModel: TasksViewModel): Boolean {
                        var pop by remember { mutableStateOf(false) }
                        val taskName = remember { mutableStateOf(TextFieldValue()) }
                        var isButtonEnabled by remember { mutableStateOf(false) }
                        // Handling date picker
                        var showDialog by remember { mutableStateOf(false) }
                        val datePickerState = rememberDatePickerState()
                        val selectedDate = datePickerState.selectedDateMillis?.let {
                            convertMillisToDate(it)
                        }
                        // Creating a text field for tasks name
                        TextField(
                            value = taskName.value,
                            onValueChange = {
                                taskName.value = it
                                isButtonEnabled = it.text.isNotEmpty()
                            },
                            placeholder = {
                                Text(text = "Enter task")
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                            singleLine = true, colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )

                        Row {
                            Button(onClick = { showDialog = true }) {
                                Icon(Icons.Default.DateRange, contentDescription = "date-picker")
                            }
                            if (selectedDate != null) Text(text = selectedDate.toString())

                            if (showDialog) {
                                DatePickerDialog(
                                    onDismissRequest = { showDialog = false },
                                    confirmButton = {
                                        Button(onClick = {
                                            showDialog = false
                                            // do something with the selected date
                                        }) {
                                            Text("OK")
                                        }
                                    },
                                    content = {
                                        DatePicker(state = datePickerState)
                                    }
                                )
                            }
                            // Adding a button to add tasks to the database
                            Button(
                                modifier = Modifier
                                    .weight(1f) // Takes available space, pushing content to the right
                                    .wrapContentWidth(Alignment.End),
                                onClick = {
                                    viewModel.addNewTask(
                                        taskName.value.text,
                                        selectedDate.toString()
                                    )
                                    pop = true
                                },
                                enabled = isButtonEnabled
                            ) {
                                Text(text = "Save")
                            }
                        }
            return pop
        }

        @SuppressLint("SimpleDateFormat")
        private fun convertMillisToDate(millis: Long): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            return formatter.format(Date(millis))
        }


    }



