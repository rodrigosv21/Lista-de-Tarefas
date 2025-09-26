package com.example.room.ui.actives.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItensTarefasBinding
import com.example.room.entity.TarefaEntity
import com.example.room.ui.actives.viewholder.TarefaViewHolder

class TarefaAdapter : RecyclerView.Adapter<TarefaViewHolder>() {

    private var tarefa: List<TarefaEntity> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarefaViewHolder {
        val view = ItensTarefasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TarefaViewHolder,
        position: Int
    ) {
        holder.buscar(tarefa[position])
    }

    fun updateBooks(list: List<TarefaEntity>) {
        tarefa = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tarefa.size
}