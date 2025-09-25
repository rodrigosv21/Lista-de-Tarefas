package com.example.room.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.entity.TarefaEntity

@Database(entities = [TarefaEntity::class], version = 1)
abstract class TarefaDataBase: RoomDatabase() {

    abstract fun tarefaDAO(): TarefaDAO

    companion object {

        // Nome do banco de dados
        private const val DATABASE_NAME = "tarefaDB"

        // Padrão Singleton
        private lateinit var instance: TarefaDataBase

        // Padrão Singleton
        fun getDatabase(context: Context): TarefaDataBase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, TarefaDataBase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

}