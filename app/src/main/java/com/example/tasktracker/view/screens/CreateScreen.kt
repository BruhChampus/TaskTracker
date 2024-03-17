package com.example.tasktracker.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.Utils
import com.example.tasktracker.view.ui.theme.colorsList
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.data.room.TaskTrackerDatabase
import com.example.tasktracker.view.pickers.MyTimePicker
import com.example.tasktracker.view.ui.viewmodel.viewmodels.CreateScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.factories.CreateScreenViewModelFactory


@Composable
private fun giveCreateScreenViewModel(): CreateScreenViewModel {
    val dao =
        TaskTrackerDatabase.getDatabaseInstance(context = LocalContext.current.applicationContext)
            .taskCardsDao()
    return viewModel(factory = CreateScreenViewModelFactory(TasksRepositoryImpl(dao))) as CreateScreenViewModel
}

//TODO каким то образом происходдят вызовы в вьюмодель HomeScreen
@Composable
fun CreateScreen(createScreenViewModel: CreateScreenViewModel = giveCreateScreenViewModel()) {
    val createScreenUIState = createScreenViewModel.uiState.collectAsState()


    val taskTitleState = createScreenViewModel.taskTitle.observeAsState()

    val taskContentState = createScreenViewModel.taskContent.observeAsState()

    val context = LocalContext.current




    Column(modifier = Modifier.padding(8.dp)) {

        //Updating dialog(if operation takes too long)
        if (createScreenUIState.value.savingData) {
            Dialog(onDismissRequest = {//TODO
            }) {
                Column(modifier = Modifier
                    .size(100.dp)
                    .background(Color.Black)) {
                    CircularProgressIndicator(
                        color =
                        if (createScreenUIState.value.savingData) Color.Green else Color.Red
                    )
                }
            }
        }


        DatePickerView()
        TimePickerView()

        OutlinedTextField(
            value = taskTitleState.value ?: "",
            onValueChange = {
                createScreenViewModel.updateTaskTitle(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(vertical = 4.dp),
            label = { Text(text = "Task card title") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,

                cursorColor = MaterialTheme.colorScheme.secondary,

                disabledContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary
            )
        )

        OutlinedTextField(
            value = taskContentState.value ?: "",
            onValueChange = { createScreenViewModel.updateTaskContent(it) },
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text(text = "Task card content") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,

                cursorColor = MaterialTheme.colorScheme.secondary,

                disabledContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary
            )
        )
        Button(
            onClick = {
                if(createScreenViewModel.timeValue.value == "Set time" || createScreenViewModel.dateValue.value == "Set date"){
                    Utils.showToast(context, "Please, set date and time")
                }else {
                    val generatedTaskCard = TaskCard(
                        content = createScreenViewModel.taskContent.value!!,
                        title = createScreenViewModel.taskTitle.value!!,
                        time = createScreenViewModel.timeValue.value!!,
                        cardColor = Utils.randomizeColor(colorsList = colorsList).toArgb(),
                        dateInMillis = createScreenViewModel.dateInMillis.value ?: 0L
                    )
                    createScreenViewModel.sendTaskCardToDB(generatedTaskCard)

                    createScreenViewModel.sendTaskCardScheduledDateToDB(
                        TaskCardScheduledDate(
                            createScreenViewModel.dateInMillis.value ?: 0L
                        )
                    )
                    Utils.showToast(context, "Task successfully saved")
                    createScreenViewModel.clearAllFields()
                }},
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .weight(0.08f)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Save", color = MaterialTheme.colorScheme.primary)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.DatePickerView(createScreenViewModel: CreateScreenViewModel = giveCreateScreenViewModel()) {
    val dateTextState = createScreenViewModel.dateValue.observeAsState()


    val showDatePicker = remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    val context = LocalContext.current


    //Date picker
    Button(
        onClick = { showDatePicker.value = true },
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
            .padding(vertical = 8.dp)
    ) {

        Text(text = dateTextState.value ?: "")
        if (showDatePicker.value) {
            DatePickerDialog(onDismissRequest = { /*TODO*/ },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker.value = false
                        }
                    ) { Text(text = "Cancel", color = MaterialTheme.colorScheme.secondary) }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            createScreenViewModel.getDateTimeInMillis(
                                datePickerState,
                                showDatePicker
                            )
                            //If date goes before our current date show toast
                            if (createScreenViewModel.isWrongDate.value!!) {
                                Utils.showToast(
                                    context,
                                    "Selected date should be today or further, please select again"
                                )
                            }
                        }
                    ) { Text(text = "OK", color = MaterialTheme.colorScheme.secondary) }
                })
            {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = MaterialTheme.colorScheme.secondary,
                        todayDateBorderColor = Color.Red,
                        todayContentColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    }


}

@Composable
fun ColumnScope.TimePickerView(createScreenViewmodel: CreateScreenViewModel = giveCreateScreenViewModel()) {

    val showTimePicker = remember { mutableStateOf(false) }

    val timeState = remember {
        mutableStateOf("-")
    }

    val timeText = createScreenViewmodel.timeValue.observeAsState()

    //Time picker
    Button(
        onClick = { showTimePicker.value = true },
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
            .padding(vertical = 8.dp)
    ) {
        Text(text = timeText.value ?: "")


        if (showTimePicker.value) {
            MyTimePicker(
                onDismissRequest = { /*TODO*/ },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePicker.value = false
                        }
                    ) { Text(text = "Cancel", color = MaterialTheme.colorScheme.secondary) }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showTimePicker.value = false
                        }
                    ) { Text(text = "OK", color = MaterialTheme.colorScheme.secondary) }
                },
                timeState = timeState,
                is24Hours = false
            )
            createScreenViewmodel.updateTimeValue(timeState.value)
        }
    }
}