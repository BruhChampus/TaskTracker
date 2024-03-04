package com.example.tasktracker.view.ui.UiState

sealed interface UiStates {
      data object Loading : UiStates
    data object NoNetwork:UiStates

}