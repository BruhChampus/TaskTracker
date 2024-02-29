package com.example.tasktracker.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.tasktracker.data.model.ScheduledDateWithTaskCard
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.room.TaskCardsDAO
import kotlinx.coroutines.flow.Flow

interface TasksRepository{
    fun getAllTaskCards(): Flow<List<TaskCard>>

    suspend fun getTaskCardById(id: Int): TaskCard

    suspend fun insertTaskCard(taskCard: TaskCard)

    suspend fun deleteTaskCardById(id: Int)

    suspend fun getScheduledDateWithTaskCard(dateInMillis:Long):List<ScheduledDateWithTaskCard>
}