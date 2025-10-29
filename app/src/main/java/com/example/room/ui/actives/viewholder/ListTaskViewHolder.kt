package com.example.room.ui.actives.viewholder

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ListTasksBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskPriorityType
import com.example.room.listener.ListListener

class ListTaskViewHolder(
    private val item: ListTasksBinding,
    private val listener: ListListener
) : RecyclerView.ViewHolder(item.root) {

    fun bind(task: TaskEntity) {
        // Configura textos
        item.retTitulo.text = task.title.uppercase()
        item.retDescricao.text = task.description.uppercase()
        item.retPrioridade.text = task.priority.uppercase()

        // Configura checkbox inicial sem disparar listener
        item.retCheque.setOnCheckedChangeListener(null)
        item.retCheque.isChecked = task.isChecked
        item.retCheque.isEnabled = true

        // Aplica strike-through inicial
        updateStrikeThrough(task.isChecked)

        // Clique no título abre detalhes
        item.retTitulo.setOnClickListener { listener.onClick(task.id) }

        // Clique no checkbox
        item.retCheque.setOnCheckedChangeListener { _, isChecked ->
            updateStrikeThrough(isChecked)
            if (isChecked) {
                listener.onCheck(task.id)
            } else {
                listener.onUncheck(task.id)
            }
        }

        // Atualiza cor do checkbox conforme prioridade
        val colorRes = when (task.priority) {
            TaskPriorityType.NONE.toString() -> R.color.col_none
            TaskPriorityType.LOW.toString() -> R.color.col_cheq_low
            TaskPriorityType.AVERAGE.toString() -> R.color.col_cheq_average
            TaskPriorityType.HIGH.toString() -> R.color.col_cheq_high
            else -> R.color.col_none
        }
        item.retCheque.buttonTintList = item.retCheque.context.getColorStateList(colorRes)
        item.retPrioridade.text = task.priority
    }

    private fun updateStrikeThrough(isChecked: Boolean) {
        if (isChecked) {
            item.retTitulo.paintFlags = item.retTitulo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            item.retDescricao.paintFlags =
                item.retDescricao.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            item.retTitulo.paintFlags =
                item.retTitulo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            item.retDescricao.paintFlags =
                item.retDescricao.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
