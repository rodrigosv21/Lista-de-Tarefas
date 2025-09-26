package com.example.room.ui.actives.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItensTarefasBinding
import com.example.room.entity.TarefaEntity

class TarefaViewHolder(private val item: ItensTarefasBinding) : RecyclerView.ViewHolder(item.root) {

    fun buscar(tarefa: TarefaEntity) {
        item.retTitulo.text = tarefa.titulo
        item.retDescricao.text = tarefa.descricao
    }
}

