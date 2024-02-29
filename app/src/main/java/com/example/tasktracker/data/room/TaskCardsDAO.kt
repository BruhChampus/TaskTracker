package com.example.tasktracker.data.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.tasktracker.data.model.ScheduledDateWithTaskCard
import com.example.tasktracker.data.model.TaskCard
import kotlinx.coroutines.flow.Flow


interface TaskCardsDAO {

    @Query("SELECT *FROM TASKCARD")
    fun getAllTaskCards(): Flow<List<TaskCard>>

    @Query("SELECT *FROM TaskCard WHERE id = :id")
    suspend fun getTaskCardById(id: Int): TaskCard

    @Insert
    suspend fun insertTaskCard(taskCard: TaskCard)

    @Delete
    suspend fun deleteTaskCardById(id: Int)


    @Transaction
    @Query("SELECT *FROM TASKCARDSCHEDULEDDATE WHERE dateInMillis = :dateInMillis")
    suspend fun getScheduledDateWithTaskCard(dateInMillis:Long):List<ScheduledDateWithTaskCard>
}