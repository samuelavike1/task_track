package com.jhoy.tasktrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val dueLabel: String,
    val category: String,
    val isCompleted: Boolean = false
)
