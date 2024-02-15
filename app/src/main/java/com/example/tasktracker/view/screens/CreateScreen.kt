package com.example.tasktracker.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .border(
                    BorderStroke(12.dp, Color.Cyan)
                )
        ) {}
        OutlinedTextField(
            value = textTitleState.value,
            onValueChange = {textTitleState.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(vertical = 8.dp),
            label = { Text(text = "Task card title")},
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black,
                focusedContainerColor =  Color.White,

                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        OutlinedTextField(
            value = textContentState.value,
            onValueChange = {textContentState.value = it},
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text(text = "Task card content")},
          colors = TextFieldDefaults.colors(
             focusedIndicatorColor = Color.Black,
              focusedLabelColor = Color.Black,
              focusedContainerColor =  Color.White,

              disabledContainerColor = Color.White,
              unfocusedContainerColor = Color.White
          )
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(0.08f)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Save")

        }
    }
}