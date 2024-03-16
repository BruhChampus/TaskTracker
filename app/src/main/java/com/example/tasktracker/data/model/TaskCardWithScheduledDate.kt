package com.example.tasktracker.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


//1 to n Relation
data class TaskCardWithScheduledDate(
    @Embedded
    val taskCardScheduledDate:TaskCardScheduledDate,
    @Relation(
        parentColumn = "dateInMillis",
        entityColumn = "dateInMillis"
    )
    val taskCardsList: List<TaskCard>
)
