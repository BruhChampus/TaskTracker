package com.example.tasktracker.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduledDateWithTaskCard(
    @Embedded
    val taskCard: TaskCard,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskCardId"
    )
    val taskCardScheduledDate:TaskCardScheduledDate
)
