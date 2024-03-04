package com.example.tasktracker.view.ui.UiState

import com.example.tasktracker.data.model.TaskCard

data class HomeScreenUiState(val isLoading:Boolean = false,
    val tasksList:List<TaskCard> = emptyList()
)