package com.example.tasktracker.view.ui.UiState

import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardWithScheduledDate

data class DoneScreenUiState(
    val isLoading:Boolean = false,
    val taskCardWithScheduledDateList: List<TaskCardWithScheduledDate> = emptyList()
)