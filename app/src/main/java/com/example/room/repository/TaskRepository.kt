package com.example.room.repository

import android.content.Context
import com.example.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository private constructor(context: Context) {

    private val taskDAO = TaskDataBase.getDatabase(context).taskDAO()

    companion object {
        @Volatile private var instance: TaskRepository? = null
        fun getInstance(context: Context): TaskRepository {
            return instance ?: synchronized(this) {
                val tempInstance = TaskRepository(context)
                instance = tempInstance
                tempInstance
            }
        }
    }

    suspend fun newTask(task: TaskEntity) = taskDAO.newTask(task)
    fun searchAll(): Flow<List<TaskEntity>> = taskDAO.searchAll()
    suspend fun searchById(id: Int) = taskDAO.searchById(id)
    suspend fun update(task: TaskEntity) = taskDAO.update(task)
    suspend fun delete(task: TaskEntity) = taskDAO.delete(task)
}
