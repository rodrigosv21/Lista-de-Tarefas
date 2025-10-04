package com.example.room.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room.entity.TaskEntity

//my queries
@Dao
interface taskDAO {

    @Insert
    fun newTask(newTaskEntity: TaskEntity)

    @Query("SELECT * FROM task")
    fun searchAll(): List<TaskEntity>

    @Query("SELECT * FROM task WHERE id = :id")
    fun searchById(id: Int): TaskEntity

    @Delete
    fun delete(task: TaskEntity)

    @Update
    fun update(task: TaskEntity)


}