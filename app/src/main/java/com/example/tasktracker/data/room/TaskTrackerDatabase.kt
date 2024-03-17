package com.example.tasktracker.data.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.tasktracker.data.model.TaskCardWithScheduledDate
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate


@Database(
    entities = [TaskCard::class,
        TaskCardScheduledDate::class,
        ], version = 3, exportSchema = false
)
abstract class TaskTrackerDatabase : RoomDatabase() {
    abstract fun taskCardsDao(): TaskTrackerDAO

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
                    )        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}