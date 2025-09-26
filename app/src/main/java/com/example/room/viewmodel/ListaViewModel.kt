package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.room.entity.TarefaEntity
import com.example.room.repository.TarefaRepository

class ListaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TarefaRepository =
        TarefaRepository.getInstance(application.applicationContext)

    private val _tarefas = MutableLiveData<List<TarefaEntity>>()
    val tarefas: LiveData<List<TarefaEntity>> = _tarefas

    init {
        if (repository.buscarTodos().isEmpty()){
            repository.buscarTodos()
        }
    }

    fun buscarTodos(){
        _tarefas.value = repository.buscarTodos()
    }

}