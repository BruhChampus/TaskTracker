package com.example.tasktracker.view.pickers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun myTimePicker() {
    Card(shape = RoundedCornerShape(10)) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .height(500.dp)
                .padding(24.dp)
        ) {
            Text(text = "Select Time")
            OutlinedTextField(
                value = "JOPA",
                onValueChange = { "" },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(10),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedPlaceholderColor = Color.Cyan,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        }
    }
}