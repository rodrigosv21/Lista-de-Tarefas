package com.example.room.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.room.entity.TarefaEntity

@Dao
interface TarefaDAO {

    @Insert
    fun novaTarefa(tarefa: TarefaEntity)

    @Query("SELECT * FROM tarefa")
    fun buscarTodos(): List<TarefaEntity>

    @Query("SELECT * FROM tarefa WHERE id = :id")
    fun buscarPorId(id: Int): TarefaEntity

    @Delete
    fun deletar(tarefa: TarefaEntity)

}