package com.example.todo.tasks

import android.annotation.SuppressLint
<<<<<<< HEAD
import android.os.Bundle
=======
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
<<<<<<< HEAD
import androidx.compose.foundation.layout.height
=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
<<<<<<< HEAD
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
=======
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
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
<<<<<<< HEAD
import androidx.compose.material.icons.filled.ArrowDropUp
=======
import androidx.compose.ui.platform.LocalContext
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import androidx.compose.runtime.collectAsState
<<<<<<< HEAD
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
=======
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
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
               Box(Modifier.fillMaxSize()){
<<<<<<< HEAD
                   Column (modifier = Modifier.blur(if(pop)15.dp else 0.dp)
                       .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp)

                   )){
=======
                   Column {
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
                       TopAppBar(
                           colors = TopAppBarDefaults.topAppBarColors(
                               containerColor = MaterialTheme.colorScheme.primaryContainer,
                               titleContentColor = MaterialTheme.colorScheme.primary,
                           ),
                           title = {
                               Text("Tasks")
                           }
                       )
<<<<<<< HEAD
                       TasksDrawer(viewModel)
=======
                       ShowTasks(LocalContext.current, viewModel)
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83

                   }
                   if(!pop){
                   Box(modifier = Modifier.align(Alignment.BottomEnd)){
                       if(Button()) pop = true
                   }}
                   if(pop){
                   Box(
                       Modifier
                           .align(Alignment.BottomStart)
                   ){
<<<<<<< HEAD
                       if(AddTask(viewModel)) pop = false
                    }
                   }
               }
=======
                       if(AddTask(LocalContext.current, viewModel)) pop = false
                    }
                   } }
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
            }
        }
    }

<<<<<<< HEAD
    @Composable
    fun TasksDrawer(viewModel: TasksViewModel) {
        val tasksListNotMarked by viewModel.tasksListNotMarked.collectAsState()
        val tasksListMarked by viewModel.tasksListMarked.collectAsState()
        val (list2Open, setList2Open) = remember { mutableStateOf(true) }

        LazyColumn(modifier = Modifier
            .background(color = Color.Black.copy(alpha = 0.5f))
            .fillMaxWidth()) {
                itemsIndexed(tasksListNotMarked) { index, _ ->
                    LayoutCardTask(tasksListNotMarked[index], viewModel)
                }
            if(tasksListMarked.isNotEmpty()){
            // List 2 header with toggle button
            item {
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable { setList2Open(!list2Open) }
                ) {
                    Text(text = "Finished", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
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
                    onCheckedChange = { isChecked = it
                        a = if (isChecked) 1 else 0
                        viewModel.updateTasks(
                            TasksModel(
                                tasksCod = task.tasksCod,
                                tasksName = task.tasksName,
                                tasksDuration = task.tasksDuration,
                                tasksIsSelected =  a
                            )
                        )
                    }
                )
            TaskViewFields(task)
            }
    }

=======

    @Composable
    fun ShowTasks(context: Context, viewModel: TasksViewModel) {
        val tasksList by viewModel.tasksList.collectAsState()
        Column {


        BackHandler {
            // Move the app to the background when the back button is pressed
            (context as? Activity)?.moveTaskToBack(true)
        }
        Column(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.5f))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn {
                itemsIndexed(tasksList) { index, _ ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(width = 0.dp, color = Color.Transparent)
                            .fillMaxWidth()

                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            var a: Int

                            var isChecked by remember { mutableStateOf(false) }

                            CircularCheckbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked = it
                                    a = if (isChecked) 1 else 0
                                    viewModel.updateTasks(
                                        TasksModel(
                                            tasksCod = index+1,
                                            tasksName = tasksList[index].tasksName,
                                            tasksDuration = tasksList[index].tasksDuration,
                                            tasksIsSelected =  a
                                        )
                                    )
                                    Toast.makeText(
                                        context,
                                        "Tasks Updated to Database",
                                        Toast.LENGTH_SHORT
                                    ).show()}
                            )

                            Column {
                                Text(
                                    text = tasksList[index].tasksName,
                                    modifier = Modifier.padding(4.dp),
                                    textAlign = TextAlign.Start,
                                    color = Color.White // Set text color to white
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = tasksList[index].tasksDuration,
                                    modifier = Modifier.padding(4.dp),
                                    textAlign = TextAlign.Start,
                                    color = Color.White // Set text color to white
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }
        } }
    }


>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
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
<<<<<<< HEAD
                    color = if (checked) Color.Green else Color.Transparent,
                    shape = CircleShape
                ).border(
                    width = 1.dp,
                    color = Color.White,
=======
                    color = if (checked) Color.Green else Color.White,
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
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

<<<<<<< HEAD
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
            if(task.tasksDuration!="null"){
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
        fun Button(): Boolean {
            var pop by remember { mutableStateOf(false) }
            FloatingActionButton(modifier = Modifier.padding(end = 16.dp, bottom = 20.dp),onClick = { pop = true}) {
=======
        @Composable
        fun Button(): Boolean {
            var pop by remember { mutableStateOf(false) }
            FloatingActionButton(onClick = { pop = true}) {
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
            return pop
        }


<<<<<<< HEAD
@Composable
fun AddTask(viewModel: TasksViewModel):Boolean {
=======
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(context: Context, viewModel: TasksViewModel):Boolean {
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    var pop by remember { mutableStateOf(false) }
    var boxPosition by remember { mutableStateOf(DpOffset(5.dp, 0.dp)) }
    BackHandler {
        pop = true
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd // Align to the bottom
    ) {
        // Floating Box
        Box(
            modifier = Modifier
                .size(10.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, _, _ ->
                        boxPosition = DpOffset(
                            boxPosition.x + 0.dp,
                            boxPosition.y + 0.dp
                        )
                    }
                }
                .graphicsLayer(
                    translationX = boxPosition.x.value,
                    translationY = boxPosition.y.value
                )
        )
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            // Creating a column
            Column(
                modifier = Modifier
                    .padding(all = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
<<<<<<< HEAD
                pop = TaskInputFields(viewModel)
        }
    }
}
    return pop
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputFields(viewModel: TasksViewModel): Boolean {
    var pop by remember { mutableStateOf(false) }
    val taskName = remember { mutableStateOf(TextFieldValue()) }
    var isButtonEnabled by remember { mutableStateOf(false)}
    // Handling date picker
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    // Creating a text field for tasks name
    TextField(
        value = taskName.value,
        onValueChange = { taskName.value = it
            isButtonEnabled = it.text.isNotEmpty()
        },
        placeholder = { Text(text = "Enter task")
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
        Button(modifier = Modifier
            .weight(1f) // Takes available space, pushing content to the right
            .wrapContentWidth(Alignment.End),
            onClick = {
                viewModel.addNewTask(taskName.value.text, selectedDate.toString())
                pop = true
            },
            enabled = isButtonEnabled) {
            Text(text = "Save")
        }
    }
=======
                val taskName = remember { mutableStateOf(TextFieldValue()) }
                // Creating a text field for tasks name
                TextField(
                    value = taskName.value,
                    onValueChange = { taskName.value = it },
                    placeholder = { Text(text = "Enter task") },
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    singleLine = true, colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )

                // Handling date picker
                var showDialog by remember { mutableStateOf(false) }
                val datePickerState = rememberDatePickerState()
                val selectedDate = datePickerState.selectedDateMillis?.let {
                    convertMillisToDate(it)
                }
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
                    Button(modifier = Modifier
                        .weight(1f) // Takes available space, pushing content to the right
                        .wrapContentWidth(Alignment.End),
                        onClick = {
                            viewModel.addNewTask(taskName.value.text, selectedDate.toString())
                            Toast.makeText(context, "Tasks Added to Database", Toast.LENGTH_SHORT)
                                .show()
                            pop = true
                        }) {
                        Text(text = "Save")
                    }
                }
        }
    }
}
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    return pop
}

    @SuppressLint("SimpleDateFormat")
    private fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }

<<<<<<< HEAD
}
=======
}



>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
