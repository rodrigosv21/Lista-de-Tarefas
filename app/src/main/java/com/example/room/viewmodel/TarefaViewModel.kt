package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository

class TarefaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository.getInstance(application.applicationContext)


    fun saveTask(entity: TaskEntity) {
        repository.newTask(entity)
    }


}