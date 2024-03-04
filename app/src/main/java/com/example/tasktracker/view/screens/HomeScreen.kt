package com.example.tasktracker.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.data.room.TaskTrackerDatabase
import com.example.tasktracker.view.ui.viewmodel.CreateScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.factories.CreateScreenViewModelFactory
import com.example.tasktracker.view.ui.viewmodel.HomeScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.factories.HomeScreenViewModelFactory


@Composable
fun giveHomeScreenViewModel(): HomeScreenViewModel {
    val dao =
        TaskTrackerDatabase.getDatabaseInstance(context = LocalContext.current.applicationContext)
            .taskCardsDao()
    return viewModel(factory = HomeScreenViewModelFactory(TasksRepositoryImpl(dao))) as HomeScreenViewModel
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = giveHomeScreenViewModel()) {//list<TaskCard>

    val createScreenUIState = homeScreenViewModel.uiState.collectAsState()
    homeScreenViewModel.getAllTaskCards()


    Log.i("TaskSList", createScreenUIState.value.tasksList.toString())

    LazyColumn() {
        items(createScreenUIState.value.tasksList.size) {
            TaskCardScreen(createScreenUIState.value.tasksList[it])
        }
    }
}