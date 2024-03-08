package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//TODO fix
@Entity(
    primaryKeys = ["taskCardId"],
    foreignKeys = [ForeignKey(
        entity = TaskCard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskCardId"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class TaskCardScheduledDate(
    val taskCardId: Int = 0,
    val dateInMillis: Long
)
