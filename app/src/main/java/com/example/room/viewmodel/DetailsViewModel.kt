package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository = TaskRepository.getInstance(application)

    private val _task = MutableLiveData<TaskEntity>()
    val task: LiveData<TaskEntity> = _task

    fun updateTask(id: Int) {
        _task.value = repository.searchById(id)
    }

}