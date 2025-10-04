package com.example.room.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.entity.TaskEntity

// Classe de banco de dados
@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDAO(): taskDAO

    companion object {

        // Nome do banco de dados
        private const val DATABASE_NAME = "taskDB"

        // Padrão Singleton
        private lateinit var instance: TaskDataBase

        // Padrão Singleton
        fun getDatabase(context: Context): TaskDataBase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, TaskDataBase::class.java, DATABASE_NAME)
                            .addMigrations(Migrations.migrationFromV1ToV2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

    // Migrações
    private object Migrations {
        val migrationFromV1ToV2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM task")
            }
        }
    }

}