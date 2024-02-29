package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TaskCardScheduledDate(
    @PrimaryKey
    val taskCardId:Int,
    val dateInMillis:Long
)
