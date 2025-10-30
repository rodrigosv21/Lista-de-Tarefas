package com.example.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks = repository.searchAll().asLiveData()

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }

    fun toggleCheck(task: TaskEntity) = viewModelScope.launch {
        val updated = task.copy(isChecked = !task.isChecked)
        repository.update(updated)
    }

    suspend fun searchById(id: Int) = repository.searchById(id)
}
