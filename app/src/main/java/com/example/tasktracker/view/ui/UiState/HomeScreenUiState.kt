package com.example.tasktracker.view.ui.UiState

import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import com.example.tasktracker.data.model.TaskCardWithScheduledDate

data class HomeScreenUiState(
    val isLoading:Boolean = false,
    val notDoneTasksList: List<TaskCard> = emptyList(),
    val taskCardWithScheduledDateList:  TaskCardWithScheduledDate = TaskCardWithScheduledDate(
        TaskCardScheduledDate(0L),emptyList())
    )
