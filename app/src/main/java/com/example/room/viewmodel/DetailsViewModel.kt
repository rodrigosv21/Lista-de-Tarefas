package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository.getInstance(application)

    private val _task = MutableLiveData<TaskEntity?>()
    val task: LiveData<TaskEntity?> = _task

    private val _taskDeleted = MutableLiveData<Boolean>()
    val taskDeleted: LiveData<Boolean> = _taskDeleted

    // Carrega a tarefa pelo ID
    fun loadTask(id: Int) = viewModelScope.launch {
        _task.value = repository.searchById(id)
    }

    // Atualiza a tarefa
    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
        _task.value = task
    }

    // Deleta a tarefa
    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
        _taskDeleted.value = true
    }
}
