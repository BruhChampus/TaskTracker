package com.example.tasktracker.view.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.UiState.DoneScreenUiState
import com.example.tasktracker.view.ui.UiState.HomeScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoneScreenViewModel(private val taskTrackerRepo: TasksRepositoryImpl) : ViewModel() {

    private val _uiState = MutableStateFlow(DoneScreenUiState())
    val uiState: StateFlow<DoneScreenUiState>
        get() = _uiState.asStateFlow()


    init {
        getAllDoneTaskCards()
    }


    private fun getAllDoneTaskCards() {
        viewModelScope.launch(Dispatchers.IO) {
            taskTrackerRepo.getAllDoneTaskCards().collect() { taskCardList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = true,
                    )
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        doneTasksList = taskCardList
                    )
                }
            }

        }
    }

}