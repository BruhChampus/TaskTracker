package com.example.tasktracker.view.ui.viewmodel.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.UiState.DoneScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoneScreenViewModel(private val taskTrackerRepo: TasksRepositoryImpl) : ViewModel() {



    private val _doneScreenUiState = MutableStateFlow(DoneScreenUiState())
    val doneScreenUiState: StateFlow<DoneScreenUiState>
        get() = _doneScreenUiState.asStateFlow()


    init {
        getScheduledDateWithTaskCards()
    }

    private fun getScheduledDateWithTaskCards() {
        viewModelScope.launch(Dispatchers.IO) {
            taskTrackerRepo.getAllScheduledDateWithTaskCards().collect{ taskCardsWithScheduledDate ->
                _doneScreenUiState.update { currentState ->
                    currentState.copy(
                        taskCardWithScheduledDateList = taskCardsWithScheduledDate
                    )
                }
            }
        }
    }
}