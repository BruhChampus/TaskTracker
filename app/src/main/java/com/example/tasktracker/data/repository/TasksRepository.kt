package com.example.tasktracker.data.repository

import androidx.room.Query
import com.example.tasktracker.data.model.TaskCardWithScheduledDate
import com.example.tasktracker.data.model.TaskCard
import kotlinx.coroutines.flow.Flow

interface TasksRepository{
    fun getAllTaskCards(): Flow<List<TaskCard>>
    fun getAllDoneTaskCards(): Flow<List<TaskCard>>
    fun getAllNotDoneTaskCards(): Flow<List<TaskCard>>

    suspend fun getTaskCardById(id: Int): TaskCard

    suspend fun insertTaskCard(taskCard: TaskCard)

    suspend fun deleteTaskCard(taskCard: TaskCard)

      //fun getScheduledDateWithTaskCard(dateInMillis:Long):Flow<List<TaskCardWithScheduledDate>>
}