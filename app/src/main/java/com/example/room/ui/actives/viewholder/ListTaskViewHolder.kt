package com.example.room.ui.actives.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ListTasksBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskConstants
import com.example.room.listener.ListListener

class ListTaskViewHolder(
    private val item: ListTasksBinding,
    private val listener: ListListener
) : RecyclerView.ViewHolder(item.root) {

    fun bind(task: TaskEntity) {
        item.retTitulo.text = task.title.uppercase()
        item.retDescricao.text = task.description.uppercase()
        item.retPrioridade.text = task.priority.uppercase()

        // Clique no título abre detalhes
        item.retTitulo.setOnClickListener { listener.onClick(task.id) }

        item.retCheque.setOnClickListener {
            if (item.retCheque.isChecked) {
                listener.onCheck(task.id)
            } else {
                listener.onUncheck(task.id)
            }
        }

        when (task.priority) {
            TaskConstants.PRIORITY.NONE -> item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_none)

            TaskConstants.PRIORITY.LOW -> item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_low)

            TaskConstants.PRIORITY.AVERAGE -> item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_average)

            TaskConstants.PRIORITY.HIGH -> item.retCheque.buttonTintList =
                item.retCheque.context.getColorStateList(R.color.col_cheq_high)


        }

        // Configura prioridade
        setPrioridade(task)
    }

    private fun setPrioridade(entity: TaskEntity) {
        // Define estado inicial
        item.retCheque.isEnabled = true
        item.retCheque.isChecked = entity.isChecked

        // Atualiza texto de acordo com prioridade inicial
        item.retPrioridade.text = when (entity.priority) {
            TaskConstants.PRIORITY.LOW -> entity.priority
            TaskConstants.PRIORITY.AVERAGE -> entity.priority
            TaskConstants.PRIORITY.HIGH -> entity.priority
            else -> "none"
        }

    }
}
