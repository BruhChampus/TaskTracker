package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class TaskCardScheduledDate(
    @PrimaryKey
    val dateInMillis: Long
)
