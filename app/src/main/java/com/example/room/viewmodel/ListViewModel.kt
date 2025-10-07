package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository.getInstance(application.applicationContext)

    val tasks: LiveData<List<TaskEntity>> = repository.searchAll().asLiveData()

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }

    fun toggleCheck(task: TaskEntity) = viewModelScope.launch {
        task.isChecked = !task.isChecked
        repository.update(task)
    }

    suspend fun searchById(id: Int): TaskEntity? = repository.searchById(id)
}
