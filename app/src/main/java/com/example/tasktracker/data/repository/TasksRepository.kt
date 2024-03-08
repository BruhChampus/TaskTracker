package com.example.tasktracker.data.repository

import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import kotlinx.coroutines.flow.Flow

interface TasksRepository{
    fun getAllTaskCards(): Flow<List<TaskCard>>
    fun getAllDoneTaskCards(): Flow<List<TaskCard>>
    fun getAllNotDoneTaskCards(): Flow<List<TaskCard>>

    suspend fun getTaskCardById(id: Int): TaskCard

    suspend fun insertTaskCard(taskCard: TaskCard)

    suspend fun deleteTaskCard(taskCard: TaskCard)

    suspend fun insertTaskCardScheduledDate(taskCardScheduledDate: TaskCardScheduledDate)

      //fun getScheduledDateWithTaskCard(dateInMillis:Long):Flow<List<TaskCardWithScheduledDate>>
}