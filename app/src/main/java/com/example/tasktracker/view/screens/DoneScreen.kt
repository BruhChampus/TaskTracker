package com.example.tasktracker.view.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.data.room.TaskTrackerDatabase
import com.example.tasktracker.view.ui.viewmodel.CreateScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.DoneScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.factories.CreateScreenViewModelFactory
import com.example.tasktracker.view.ui.viewmodel.factories.DoneScreenViewModelFactory

@Composable
private fun giveDoneScreenViewModel(): DoneScreenViewModel {
    val dao =
        TaskTrackerDatabase.getDatabaseInstance(context = LocalContext.current.applicationContext)
            .taskCardsDao()
    return viewModel(factory = DoneScreenViewModelFactory(TasksRepositoryImpl(dao))) as DoneScreenViewModel
}

@Composable
fun DoneScreen(doneScreenViewModel: DoneScreenViewModel = giveDoneScreenViewModel()) {

    val homeScreenUIState = doneScreenViewModel.uiState.collectAsState()

    if (homeScreenUIState.value.doneTasksList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "No tasks yet. Please create some!",
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
                    .align(Alignment.Center)
            )
        }
    } else {
        //TODO think about how can I implement that list
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
                        text = "6 July 2024",
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
            items(homeScreenUIState.value.doneTasksList.size) {
                Box(modifier = Modifier.padding(start = 8.dp)) {
                    TaskCardView(
                        taskCard = homeScreenUIState.value.doneTasksList[it],
                        showTimeInCard = true, onCLick = {//TODO
                        }
                    )//add list of done tasks and put it in
                }
            }
        }
    }


}