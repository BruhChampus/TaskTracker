package com.example.tasktracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tasktracker.data.model.TaskCardWithScheduledDate
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskTrackerDAO {

    @Query("SELECT *FROM TaskCard")
    fun getAllTaskCards(): Flow<List<TaskCard>>

    @Query("SELECT *FROM TaskCard WHERE id = :id")
    suspend fun getTaskCardById(id: Int): TaskCard

    @Insert
    suspend fun insertTaskCard(taskCard: TaskCard)

    @Delete
    suspend fun deleteTaskCard(taskCard: TaskCard)




//    @Transaction
//    @Query("SELECT *FROM TASKCARDSCHEDULEDDATE WHERE dateInMillis = :dateInMillis")
//    fun getScheduledDateWithTaskCard(dateInMillis: Long): Flow<List<TaskCardWithScheduledDate>>


}