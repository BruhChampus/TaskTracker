package com.example.tasktracker.view.screens

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.tasktracker.view.pickers.AmPmTimePicker
import com.example.tasktracker.view.pickers.MyTimePicker
import com.example.tasktracker.view.pickers.TwentyFourHoursPicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

                cursorColor = Color.Black,

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

                cursorColor = Color.Black,

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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Save", color = MaterialTheme.colorScheme.primary)

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
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
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
                                val toast = Toast.makeText(
                                    context,
                                    "Selected date should be today or further, please select again",
                                    Toast.LENGTH_LONG
                                )
                                toast.setGravity(Gravity.TOP, 0, 0)
                                toast.show()

                            } else {
                                val dateFormatter =
                                    SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
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
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)
            .padding(vertical = 8.dp)
    ) {
        Text(text = setTimeStateText.value)


        if (showTimePicker.value) {
            val timeState = remember{
                mutableStateOf("-")
            }
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
            setTimeStateText.value = timeState.value
        }

    }
}