package com.example.tasktracker.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class TaskCardWithScheduledDate(
    @Embedded
    val taskCard: TaskCard,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskCardId"
    )
    val taskCardScheduledDate:TaskCardScheduledDate
)
