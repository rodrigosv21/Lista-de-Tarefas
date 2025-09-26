package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.room.entity.TarefaEntity
import com.example.room.repository.TarefaRepository

class TarefaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TarefaRepository.getInstance(application.applicationContext)

    fun salvarDados(entity: TarefaEntity) {
        repository.novaTarefa(entity)
    }


}