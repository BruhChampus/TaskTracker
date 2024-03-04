package com.example.tasktracker.view.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.UiState.CreateScreenUIState
import com.example.tasktracker.view.ui.UiState.HomeScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val taskTrackerRepo: TasksRepositoryImpl) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState.asStateFlow()


    fun getAllTaskCards() {
        val list =  ArrayList<TaskCard>()
        viewModelScope.launch(Dispatchers.IO) {
            taskTrackerRepo.getAllTaskCards().collect() { taskCardList ->
                _uiState.update { currentState -> currentState.copy(isLoading = true) }
                taskCardList.forEach {
                    list.add(it)
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        tasksList = list
                    )
                }
            }
        }
    }
}