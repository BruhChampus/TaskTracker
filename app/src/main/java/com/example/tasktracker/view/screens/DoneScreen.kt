package com.example.tasktracker.view.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.data.room.TaskTrackerDatabase
import com.example.tasktracker.view.taskcards.TaskCardsWithTime
import com.example.tasktracker.view.ui.viewmodel.viewmodels.DoneScreenViewModel
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

    val homeScreenUIState = doneScreenViewModel.doneScreenUiState.collectAsState()

    if (homeScreenUIState.value.taskCardWithScheduledDateList.isEmpty()) {
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
        val list = homeScreenUIState.value.taskCardWithScheduledDateList.toSet()
        TaskCardsWithTime(list)


    }
}



