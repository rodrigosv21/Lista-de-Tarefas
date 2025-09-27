package com.example.room.ui.actives.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ItensTarefasBinding
import com.example.room.entity.TarefaEntity
import com.example.room.helper.TarefasConstants

class TarefaViewHolder(private val item: ItensTarefasBinding) : RecyclerView.ViewHolder(item.root) {

    fun buscar(tarefa: TarefaEntity) {
        item.retTitulo.text = tarefa.titulo.uppercase()
        item.retDescricao.text = tarefa.descricao.uppercase()
        item.retCheque.isEnabled = tarefa.prioridade != 0

        if (tarefa.prioridade == TarefasConstants.PRIORIDADE.BAIXA) {
            item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_baixa)
        } else if (tarefa.prioridade == TarefasConstants.PRIORIDADE.MEDIA) {
            item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_media)
        } else {
            item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_alta)
        }

    }
}


