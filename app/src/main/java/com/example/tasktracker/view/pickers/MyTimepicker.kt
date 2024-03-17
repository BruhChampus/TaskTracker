package com.example.tasktracker.view.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tasktracker.Utils
import com.example.tasktracker.domain.MyTimeConverter


//TODO datepicker можеш сделать что получаем только дату начала таска, и потом когда этото день настает присылать уведомление, мол на сегодня столько то тасков, ну и все
 @Composable
fun MyTimePicker(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    timeState: MutableState<String>,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    is24Hours: Boolean = true
) {
    when (is24Hours) {
        true -> TwentyFourHoursPicker(
            title,
            onDismissRequest,
            dismissButton,
            confirmButton,
            timeState,
            containerColor
        )

        false -> AmPmTimePicker(
            title,
            onDismissRequest,
            dismissButton,
            confirmButton,
            timeState,
            containerColor
        )
    }
}

@Composable
fun TwentyFourHoursPicker(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    timeState: MutableState<String>,
    containerColor: Color = MaterialTheme.colorScheme.surface,
) {

    val hoursState = remember {
        mutableStateOf("00")
    }

    val minutesState = remember {
        mutableStateOf("00")
    }


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
            Card(shape = RoundedCornerShape(10)) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Text(text = title)
                    Row {
                        OutlinedTextField(
                            value = hoursState.value,
                            onValueChange = {
                                if (it.length <= 2) hoursState.value = it
                            },
                            textStyle = TextStyle.Default.copy(
                                fontSize = 46.sp,
                                textAlign = TextAlign.Center,
                            ),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .width(140.dp)
                                .height(100.dp)
                                .padding(8.dp),
                            shape = RoundedCornerShape(10),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,

                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,

                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                unfocusedTextColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                        Text(text = ":", fontSize = 56.sp)
                        OutlinedTextField(
                            value = minutesState.value,
                            onValueChange = {
                                if (it.length <= 2) {
                                    minutesState.value = it
                                }
                            },
                            textStyle = TextStyle.Default.copy(
                                fontSize = 46.sp,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .width(140.dp)
                                .height(100.dp)
                                .padding(8.dp),
                            shape = RoundedCornerShape(10),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,

                                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,

                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                unfocusedTextColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }
                }
                dismissButton?.invoke()
                if(hoursState.value != "" && minutesState.value != "") {
                    if (hoursState.value.toInt() >= 24 || minutesState.value.toInt() >= 60) {
                        Text(text = "Time must be in 24-hours format", color = Color.Red, modifier = Modifier.padding(8.dp))
                    } else {
                        timeState.value = "${MyTimeConverter.transformTime(hoursState.value)} : ${MyTimeConverter.transformTime(minutesState.value)}"
                        confirmButton()
                    }
                }
            }
        }
    }
}


@Composable
fun AmPmTimePicker(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    timeState: MutableState<String>,
    containerColor: Color = MaterialTheme.colorScheme.surface,
) {

    val hoursState = remember {
        mutableStateOf("00")
    }

    val minutesState = remember {
        mutableStateOf("00")
    }
    val timePeriod = remember {
        mutableStateOf("AM")
    }

    val isAmClicked = remember {
        mutableStateOf(true)
    }

    timeState.value = "${hoursState.value}:${minutesState.value} ${timePeriod.value}"

    val hours =
        listOf("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")

    val minutes = ArrayList<String>()
    for (i in 0..59 step 5) {
        minutes.add(MyTimeConverter.transformTime(i.toString()))
    }

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
            Card(shape = RoundedCornerShape(10)) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Text(text = title)
                    Row() {
                        Column() {
                            MyDropDownMenu(
                                options = hours,
                                label = { Text(text = "Hours") },
                                optionState = hoursState
                            )
                        }
                        Text(text = ":", fontSize = 56.sp)
                        Column() {
                            MyDropDownMenu(
                                options = minutes,
                                label = { Text(text = "Minutes") },
                                optionState = minutesState
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(8.dp)) {
                        OutlinedButton(
                            onClick = { timePeriod.value = "AM"
                                      isAmClicked.value = true
                                      },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isAmClicked.value) {
                                    Color.Gray
                                } else {
                                    Color.Transparent
                                }
                            )
                        ) {
                            Text(text = "AM", color = MaterialTheme.colorScheme.secondary)
                        }
                        OutlinedButton(
                            onClick = {
                                timePeriod.value = "PM"
                                isAmClicked.value = false
                            },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isAmClicked.value) {
                                    Color.Transparent
                                } else {
                                    Color.Gray
                                }
                            )
                        ) {
                            Text(text = "PM", color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
                Row(modifier = Modifier.padding(8.dp)) {
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(
    options: List<String>,
    label: @Composable () -> Unit,
    optionState: MutableState<String>
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val selectedOptionText = remember {
        mutableStateOf(options[0])
    }

    OutlinedTextField(
        readOnly = true,
        enabled = false,
        value = selectedOptionText.value,
        onValueChange = {
        },
        label = { label() },
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded.value
            )
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 46.sp,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .width(140.dp)
            .height(100.dp)
            .padding(8.dp)
            .clickable { expanded.value = !expanded.value },
        shape = RoundedCornerShape(10),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,

            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        )
    )


    if (expanded.value) {
        options.forEach { selectedOption ->
            DropDownItem(
                text = { Text(text = selectedOption) },
                onClick = {
                    selectedOptionText.value = selectedOption
                    expanded.value = false
                    optionState.value = selectedOption
                })
        }
    }
}


@Composable
fun DropDownItem(text: @Composable () -> Unit, onClick: () -> Unit) {
    Box(modifier = Modifier
        .clickable { onClick() }
        .padding(vertical = 8.dp, horizontal = 12.dp)
        .fillMaxWidth()) {
        text()
    }
}








