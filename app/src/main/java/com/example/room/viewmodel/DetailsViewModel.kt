package com.example.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _task = MutableLiveData<TaskEntity?>()
    val task: LiveData<TaskEntity?> get() = _task

    private val _taskDeleted = MutableLiveData<Boolean>()
    val taskDeleted: LiveData<Boolean> get() = _taskDeleted

    fun loadTask(id: Int) = viewModelScope.launch {
        _task.value = repository.searchById(id)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
        _taskDeleted.value = true
    }
}
