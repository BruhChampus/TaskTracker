package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = TaskCard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskCardId"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class TaskCardScheduledDate(
    @PrimaryKey
    val taskCardId: Int,
    val dateInMillis: Long
)
