package com.example.room.listener

import com.example.room.entity.TaskEntity

interface ListListener {
    fun onClick(id: Int)
    fun onPriorityAlter(taskEntity: TaskEntity)
    fun onCheck(id: Int)
    fun onUncheck(id: Int)
}
