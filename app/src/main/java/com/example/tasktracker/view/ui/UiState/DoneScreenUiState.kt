package com.example.tasktracker.view.ui.UiState

import com.example.tasktracker.data.model.TaskCard

data class DoneScreenUiState(
    val isLoading:Boolean = false,
    val doneTasksList: List<TaskCard> = emptyList()
)