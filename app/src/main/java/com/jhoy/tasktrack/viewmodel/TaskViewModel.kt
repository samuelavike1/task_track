package com.jhoy.tasktrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoy.tasktrack.model.Task
import com.jhoy.tasktrack.model.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDao: TaskDao
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addTask(title: String, description: String, category: String, dueLabel: String, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            taskDao.insertTask(
                Task(
                    title = title,
                    description = description,
                    dueLabel = dueLabel,
                    category = category,
                    isCompleted = false
                )
            )
            onComplete()
        }
    }

    fun getTask(taskId: Int): Task? = tasks.value.find { it.id == taskId }

    fun updateTask(taskId: Int, title: String, description: String, category: String, dueLabel: String, onComplete: () -> Unit = {}) {
        val currentTask = tasks.value.find { it.id == taskId } ?: return

        viewModelScope.launch {
            taskDao.updateTask(
                currentTask.copy(
                    title = title,
                    description = description,
                    category = category,
                    dueLabel = dueLabel
                )
            )
            onComplete()
        }
    }

    fun toggleTaskCompletion(taskId: Int, onComplete: () -> Unit = {}) {
        val currentTask = tasks.value.find { it.id == taskId } ?: return

        viewModelScope.launch {
            taskDao.updateTask(
                currentTask.copy(isCompleted = !currentTask.isCompleted)
            )
            onComplete()
        }
    }

    fun deleteTask(taskId: Int, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            taskDao.deleteTask(taskId)
            onComplete()
        }
    }
}
