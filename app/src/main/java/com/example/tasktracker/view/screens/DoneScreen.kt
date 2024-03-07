package com.example.tasktracker.view.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun DoneScreen() {
    Column {
        LazyColumn {
            item {
                Row {
                    Divider(
                        thickness = 2.dp,
                        color = Color.Gray,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)

                    )
                    Text(
                        text = "6 July",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Divider(
                        thickness = 2.dp,
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                }
            }
//            items(2) {
//              TaskCardView(showTimeInCard = true)//add list of done tasks and put it in
//            }
        }
    }
}