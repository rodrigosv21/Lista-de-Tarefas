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
    private var taskList: List<TaskEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTaskViewHolder {
        val binding = ListTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTaskViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ListTaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    fun attachListener(listener: ListListener) {
        this.listener = listener
    }

    fun updateTasks(list: List<TaskEntity>) {
        taskList = list
        notifyDataSetChanged()
    }
}
