package com.example.tasktracker.view.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

@Preview(showSystemUi = true)
@Composable
fun CreateScreen() {

    val textTitleState = rememberSaveable {
        mutableStateOf("")
    }
    val textContentState = rememberSaveable {
        mutableStateOf("")
    }



    Column(modifier = Modifier.padding(8.dp)) {

        Pickers()

        OutlinedTextField(
            value = textTitleState.value,
            onValueChange = { textTitleState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(vertical = 8.dp),
            label = { Text(text = "Task card title") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                 focusedLabelColor = Color.Black,
                focusedContainerColor = Color.White,

                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        OutlinedTextField(
            value = textContentState.value,
            onValueChange = { textContentState.value = it },
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text(text = "Task card content") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                focusedContainerColor = Color.White,

                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .weight(0.08f)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Save")

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.Pickers() {
    val setDateStateText = rememberSaveable {
        mutableStateOf("Set date")
    }
    val setTimeStateText = rememberSaveable {
        mutableStateOf("Set time")
    }
    val showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()
    val context = LocalContext.current

    //Date picker
    Button(
        onClick = { showDatePicker.value = true },
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
            .padding(vertical = 8.dp)
    ) {
        Text(text = setDateStateText.value)
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
                            val selectedDate = Calendar.getInstance().apply {
                                timeInMillis =
                                    datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                            }
                            val yesterdayDate = Calendar.getInstance().apply {
                                timeInMillis = (System.currentTimeMillis() - 1000 * 60 * 60 * 24)
                            }
                            if (selectedDate.before(yesterdayDate)) {
                                Toast.makeText(
                                    context,
                                    "Selected date should be today or further, please select again",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                setDateStateText.value = dateFormatter.format(selectedDate.time)
                                showDatePicker.value = false
                            }
                        }
                    ) { Text(text = "OK", color = MaterialTheme.colorScheme.secondary) }
                }) {
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

    //Time picker
    Button(
        onClick = { showTimePicker.value = true },
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
            .padding(vertical = 8.dp)
    ) {
        Text(text = setTimeStateText.value)
        if (showTimePicker.value) {
            TimePickerDialog(
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

                            timePickerState.hour
                            timePickerState.minute
                          val time = 2
                            if( time < System.currentTimeMillis()){
                                Toast.makeText(
                                    context,
                                    "Selected time should in future, please select again",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            setTimeStateText.value =       timePickerState.hour.milliseconds.toString()
                            showTimePicker.value = false

                        }
                    ) { Text(text = "OK", color = MaterialTheme.colorScheme.secondary) }
                },
                content = {
                    TimePicker(
                         state = timePickerState,
                        colors = TimePickerDefaults.colors(clockDialSelectedContentColor = MaterialTheme.colorScheme.secondary)
                    )
                }
            )

        }

    }
}




@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(shape = MaterialTheme.shapes.extraLarge, color = containerColor)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title, modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp), style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}