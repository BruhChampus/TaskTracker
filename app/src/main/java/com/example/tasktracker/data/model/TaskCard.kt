package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskCard(
    @PrimaryKey val id: Int = 0,
    val title: String,
    val content: String,
    val time: String,
    val cardColor:String)