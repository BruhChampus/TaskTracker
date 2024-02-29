package com.example.tasktracker.data.repository

import com.example.tasktracker.data.model.ScheduledDateWithTaskCard
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.room.TaskCardsDAO
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val taskCardsDAO: TaskCardsDAO): TasksRepository {
    override fun getAllTaskCards(): Flow<List<TaskCard>> {
      return  taskCardsDAO.getAllTaskCards()
    }

    override suspend fun getTaskCardById(id: Int): TaskCard {
        return taskCardsDAO.getTaskCardById(id)
    }

    override suspend fun insertTaskCard(taskCard: TaskCard) {
        taskCardsDAO.insertTaskCard(taskCard)
    }

    override suspend fun deleteTaskCardById(id: Int) {
        taskCardsDAO.deleteTaskCardById(id)
    }

    override suspend fun getScheduledDateWithTaskCard(dateInMillis: Long): List<ScheduledDateWithTaskCard> {
        return  taskCardsDAO.getScheduledDateWithTaskCard(dateInMillis)
    }
}