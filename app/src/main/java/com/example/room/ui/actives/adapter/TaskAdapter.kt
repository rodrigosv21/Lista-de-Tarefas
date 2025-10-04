package com.example.room.ui.actives.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ListTasksBinding
import com.example.room.entity.TaskEntity
import com.example.room.listener.ListListener
import com.example.room.ui.actives.viewholder.ListTaskViewHolder

class TaskAdapter : RecyclerView.Adapter<ListTaskViewHolder>() {

    private lateinit var listener: ListListener
    private var task: List<TaskEntity> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListTaskViewHolder {
        val view = ListTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTaskViewHolder(view, listener)
    }

    override fun onBindViewHolder(
        holder: ListTaskViewHolder,
        position: Int
    ) {
        holder.bind(task[position])
    }

    fun updateBooks(list: List<TaskEntity>) {
        task = list
        notifyDataSetChanged()
    }

    fun updateEstate(check: TaskEntity) {
        task = task.filter { it.id != check.id }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = task.size

    fun attachListener(listener: ListListener) {
        this@TaskAdapter.listener = listener
    }
}