package com.example.tasktracker.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.Utils
import com.example.tasktracker.randomizeColor
import com.example.tasktracker.ui.theme.PurpleGrey40
import com.example.tasktracker.ui.theme.colorsList
import com.example.tasktracker.view.model.TaskCard
import com.example.tasktracker.view.pickers.MyTimePicker
import com.example.tasktracker.viewmodel.CreateScreenViewmodel

@Composable
fun CreateScreen(createScreenViewmodel: CreateScreenViewmodel = viewModel()) {

    val taskTitleState = createScreenViewmodel.taskTitle.observeAsState()

    val taskContentState = createScreenViewmodel.taskContent.observeAsState()



     Column(modifier = Modifier.padding(8.dp)) {

        DatePickerView()
        TimePickerView()

        OutlinedTextField(
            value = taskTitleState.value ?: "",
            onValueChange = {
                createScreenViewmodel.updateTaskTitle(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(vertical = 8.dp),
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
            onValueChange = { createScreenViewmodel.updateTaskContent(it) },
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
            onClick = { /*TODO*/
                Utils.list.add(
                    TaskCard(
                        content = createScreenViewmodel.taskContent.value!!,
                        title = createScreenViewmodel.taskTitle.value!!,
                        time = createScreenViewmodel.timeValue.value!!,
                        cardColor = randomizeColor(colorsList = colorsList).toString()
                    )
                )
             },
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
fun ColumnScope.DatePickerView(createScreenViewmodel: CreateScreenViewmodel = viewModel()) {
    val dateTextState = createScreenViewmodel.dateValue.observeAsState()


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
                            createScreenViewmodel.getDateTimeInMillis(
                                datePickerState,
                                showDatePicker
                            )
                            //If date goes before our current date show toast
                            if (createScreenViewmodel.isWrongDate.value!!) {
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
fun ColumnScope.TimePickerView(createScreenViewmodel: CreateScreenViewmodel = viewModel()) {

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