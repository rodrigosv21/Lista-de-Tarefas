package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.room.entity.TaskEntity
import com.example.room.repository.TaskRepository

//ViewModel Lista de Tarefas
class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository =
        TaskRepository.getInstance(application.applicationContext)

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val task: LiveData<List<TaskEntity>> = _tasks

    private val _check = MutableLiveData<TaskEntity>()
    val check: LiveData<TaskEntity> = _check

    // Inicializa a lista de tarefas assim que instanciada
    init {
        repository.searchAll()
    }

    // Busca todas as tarefas
    fun searchAll() {
        _tasks.value = repository.searchAll()
    }

    // Busca uma tarefa pelo ID
    fun searchById(id: Int): TaskEntity {
        return repository.searchById(id)
    }

    // Atualiza uma tarefa
    fun updateList(task: TaskEntity) {
        repository.update(task)
        searchAll()
    }

    // Atualiza uma check
    fun updadeTask(check: TaskEntity) {
        repository.update(check)
        searchAll()
    }


}