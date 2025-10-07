package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository.getInstance(application.applicationContext)

    fun saveTask(entity: TaskEntity) = viewModelScope.launch {
        repository.newTask(entity)
    }
}
