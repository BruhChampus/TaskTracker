package com.example.tasktracker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasktracker.data.model.ScheduledDateWithTaskCard
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate


@Database(
    entities = [TaskCard::class,
        TaskCardScheduledDate::class,
        ScheduledDateWithTaskCard::class], version = 1
)
abstract class TaskTrackerDatabase : RoomDatabase() {
    abstract fun taskCardsDao(): TaskCardsDAO

    companion object {
        @Volatile
        private var INSTANCE: TaskTrackerDatabase? = null
        fun getDatabaseInstance(context: Context): TaskTrackerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskTrackerDatabase::class.java,
                        "task_tracker_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}