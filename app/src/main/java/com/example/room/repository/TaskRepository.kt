package com.example.room.repository

import android.content.Context
import androidx.annotation.CheckResult
import com.example.room.entity.TaskEntity

class TaskRepository private constructor(context: Context) {

    private val taskDAO = TaskDataBase.getDatabase(context).taskDAO()

    companion object {
        private lateinit var instance: TaskRepository

        /**
         * Fornece a única instância de TaskRepository.
         * Esta é uma implementação thread-safe do padrão singleton.
         */
        fun getInstance(context: Context): TaskRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = TaskRepository(context)
                }
            }
            return instance
        }

    }

    //Metodo para inserir a tarefa
    fun newTask(task: TaskEntity) {
        taskDAO.newTask(task)
    }

    //Metodo para buscar todas as tarefas
    fun searchAll(): List<TaskEntity> {
        return taskDAO.searchAll()
    }

    //Metodo para atualizar a tarefa
    fun update(checkResult: TaskEntity) {
        taskDAO.update(checkResult)
    }

    //Metodo para buscar uma tarefa pelo id
    fun searchById(id: Int): TaskEntity? {
        return taskDAO.searchById(id)
    }

    //Metodo para deletar a tarefa
    fun delete(task: TaskEntity) {
        taskDAO.delete(task)
    }
}