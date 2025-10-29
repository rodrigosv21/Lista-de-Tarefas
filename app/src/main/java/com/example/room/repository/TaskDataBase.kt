package com.example.room.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDAO(): TaskDAO

    companion object {
        private const val DATABASE_NAME = "taskDB"
        @Volatile private var instance: TaskDataBase? = null // Garantir que a instância seja visível para todas as threads

        fun getDatabase(context: Context): TaskDataBase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    DATABASE_NAME
                ).build()
                instance = tempInstance
                tempInstance
            }
        }
    }
}
