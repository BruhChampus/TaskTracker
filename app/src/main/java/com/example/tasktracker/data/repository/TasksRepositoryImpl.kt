package com.example.tasktracker.data.repository

import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import com.example.tasktracker.data.model.TaskCardWithScheduledDate
import com.example.tasktracker.data.room.TaskTrackerDAO
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(private val taskTrackerDAO: TaskTrackerDAO) : TasksRepository {
    override fun getAllTaskCards(): Flow<List<TaskCard>> {
        return taskTrackerDAO.getAllTaskCards()
    }

    override fun getAllDoneTaskCards(): Flow<List<TaskCard>> {
        return taskTrackerDAO.getAllDoneTaskCards()
    }

    override fun getAllNotDoneTaskCards(): Flow<List<TaskCard>> {
        return taskTrackerDAO.getAllNotDoneTaskCards()
    }

    override suspend fun getTaskCardById(id: Int): TaskCard {
        return taskTrackerDAO.getTaskCardById(id)
    }

    override suspend fun insertTaskCard(taskCard: TaskCard) {
        taskTrackerDAO.insertTaskCard(taskCard)
    }

    override suspend fun deleteTaskCard(taskCard: TaskCard) {
        taskTrackerDAO.deleteTaskCard(taskCard)
    }

    override suspend fun insertTaskCardScheduledDate(taskCardScheduledDate: TaskCardScheduledDate) {
        return taskTrackerDAO.insertTaskCardScheduledDate(taskCardScheduledDate)
    }

    override fun getScheduledDateWithTaskCard(dateInMillis: Long): Flow<List<TaskCardWithScheduledDate>> {
        return taskTrackerDAO.getScheduledDateWithTaskCard(dateInMillis)
    }

    override fun getAllScheduledDateWithTaskCards(): Flow<List<TaskCardWithScheduledDate>> {
        return taskTrackerDAO.getAllScheduledDateWithTaskCards()
    }


}