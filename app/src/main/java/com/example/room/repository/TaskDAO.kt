package com.example.room.repository

import androidx.room.*
import com.example.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

// Define a DAO (Data Access Object) interface to interact with the TaskEntity table
@Dao
interface TaskDAO {

    /*
    *Define o que fazer se houver conflito na hora de inserir — normalmente um conflito ocorre quando já existe um registro com a mesma chave primária (Primary Key).
    *🔹 OnConflictStrategy.REPLACE
    * Indica que, se ocorrer um conflito, o Room deve:]
    * Substituir o registro existente pelo novo
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun newTask(task: TaskEntity)

    @Query("SELECT * FROM task")
    fun searchAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun searchById(id: Int): TaskEntity?

    @Delete
    suspend fun delete(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)
}
