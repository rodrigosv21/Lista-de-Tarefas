package com.example.room.repository

import androidx.room.*
import com.example.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

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
