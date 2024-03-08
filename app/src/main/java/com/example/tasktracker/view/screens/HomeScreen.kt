package com.example.tasktracker.view.screens

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.data.room.TaskTrackerDatabase
import com.example.tasktracker.view.ui.viewmodel.HomeScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.factories.HomeScreenViewModelFactory


@Composable
private fun giveHomeScreenViewModel(): HomeScreenViewModel {
    val dao =
        TaskTrackerDatabase.getDatabaseInstance(context = LocalContext.current.applicationContext)
            .taskCardsDao()
    return viewModel(factory = HomeScreenViewModelFactory(TasksRepositoryImpl(dao))) as HomeScreenViewModel
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = giveHomeScreenViewModel()) {//list<TaskCard>

    val homeScreenUIState = homeScreenViewModel.uiState.collectAsState()

    if (homeScreenUIState.value.notDoneTasksList.isEmpty()) {
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
         LazyColumn() {
            items(homeScreenUIState.value.notDoneTasksList.size,) { index ->
                val taskCard = homeScreenUIState.value.notDoneTasksList[index]
                TaskCardScreen(
                    taskCard,
                    onClick = {
                        homeScreenViewModel.remove(
                            taskCard
                        )
                    }
                )
            }
        }
    }


}