package com.example.tasktracker.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview(showSystemUi = true)
@Composable
fun HomeScreen() {//list<TaskCard>
    LazyColumn() {
        items(2) {
            TaskCardScreen()
        }
    }
}