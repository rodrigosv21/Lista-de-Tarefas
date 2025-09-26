package com.example.room.repository

import android.content.Context
import com.example.room.entity.TarefaEntity

class TarefaRepository private constructor(context: Context){


    private val tarefaDAO = TarefaDataBase.getDatabase(context).tarefaDAO()


    companion object {
        private lateinit var instance: TarefaRepository

        /**
         * Fornece a única instância de TarefaRepository.
         * Esta é uma implementação thread-safe do padrão singleton.
         */
        fun getInstance(context: Context): TarefaRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = TarefaRepository(context)
                }
            }
            return instance
        }

    }

    fun novaTarefa(tarefa: TarefaEntity) {
        tarefaDAO.novaTarefa(tarefa)
    }

    fun buscarTodos(): List<TarefaEntity> {
        return tarefaDAO.buscarTodos()
    }

    fun buscarPorId(id: Int): TarefaEntity {
        return tarefaDAO.buscarPorId(id)
    }

    fun deletar(tarefa: TarefaEntity) {
        tarefaDAO.deletar(tarefa)
    }
}