package com.example.room.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.entity.TarefaEntity

// Classe de banco de dados
@Database(entities = [TarefaEntity::class], version = 1)
abstract class TarefaDataBase : RoomDatabase() {

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
                db.execSQL("DELETE FROM Book")
            }
        }
    }

}