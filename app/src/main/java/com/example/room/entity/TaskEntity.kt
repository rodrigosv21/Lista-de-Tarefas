package com.example.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the TaskEntity data class with Room annotations primeiro para criar a tabela "task"
@Entity("task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("title") var title: String,
    @ColumnInfo("description") var description: String,
    @ColumnInfo("priority") var priority: String,
    @ColumnInfo("is_checked") var isChecked: Boolean = false
)
