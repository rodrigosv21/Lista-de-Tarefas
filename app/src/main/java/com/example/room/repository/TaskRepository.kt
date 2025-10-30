package com.example.room.repository

import com.example.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDAO: TaskDAO
) {
    suspend fun newTask(task: TaskEntity) = taskDAO.newTask(task)
    fun searchAll(): Flow<List<TaskEntity>> = taskDAO.searchAll()
    suspend fun searchById(id: Int) = taskDAO.searchById(id)
    suspend fun update(task: TaskEntity) = taskDAO.update(task)
    suspend fun delete(task: TaskEntity) = taskDAO.delete(task)
}
