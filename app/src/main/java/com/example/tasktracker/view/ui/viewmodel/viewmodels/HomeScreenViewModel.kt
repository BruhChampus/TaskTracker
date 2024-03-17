package com.example.tasktracker.view.ui.viewmodel.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.UiState.HomeScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneOffset

class HomeScreenViewModel(private val taskTrackerRepo: TasksRepositoryImpl) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState.asStateFlow()


    init {
        getAllNotDoneTaskCards()


            val currentDate =
                LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
            getScheduledDateWithNotDoneTaskCards(currentDate)

    }

    //TODO implement
    private fun getAllNotDoneTaskCards() {
        viewModelScope.launch(Dispatchers.IO) {
            taskTrackerRepo.getAllNotDoneTaskCards().collect() { taskCardList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = true,
                    )
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        notDoneTasksList = taskCardList
                    )
                }

            }

        }
    }

    //Can match to get todays NotDone TaskCards. To homeScreenViewmodel
    //        val currentDate = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
//        getScheduledDateWithDoneTaskCards(currentDate)
    private fun getScheduledDateWithNotDoneTaskCards(dateInMills: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            taskTrackerRepo.getScheduledDateWithTaskCard(dateInMills)
                .collect { taskCardsWithScheduledDate ->
                    if (taskCardsWithScheduledDate.isNotEmpty()) {
                        _uiState.update { currentState ->
                            currentState.copy(
                                taskCardWithScheduledDateList = taskCardsWithScheduledDate[0]
                            )
                        }
                    }
                }
        }
    }

    fun removeFromScheduledTaskCard(taskCard: TaskCard) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.taskCardWithScheduledDateList
            val updatedList = uiState.value.taskCardWithScheduledDateList.taskCardsList
                .apply { remove(taskCard) }
            _uiState.update { currentState ->
                currentState.copy(
                    taskCardWithScheduledDateList = updatedList.let {
                        uiState.value.taskCardWithScheduledDateList.copy(
                            taskCardsList = it
                        )
                    }
                )
            }
            taskTrackerRepo.insertTaskCard(taskCard.copy(isDone = true))
        }

    }



fun remove(taskCard: TaskCard) {
    viewModelScope.launch(Dispatchers.IO) {
        val updatedList =
            uiState.value.notDoneTasksList.toMutableList().apply { remove(taskCard) }
        _uiState.update { currentState -> currentState.copy(notDoneTasksList = updatedList) }
        taskTrackerRepo.insertTaskCard(taskCard.copy(isDone = true))
    }
}
}