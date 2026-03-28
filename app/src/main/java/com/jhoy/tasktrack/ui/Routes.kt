package com.jhoy.tasktrack.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTask : Screen("add_task")
    object TaskDetail : Screen("task_detail/{taskId}") {
        const val taskIdArg = "taskId"

        fun createRoute(taskId: Int): String = "task_detail/$taskId"
    }

    object EditTask : Screen("edit_task/{taskId}") {
        const val taskIdArg = "taskId"

        fun createRoute(taskId: Int): String = "edit_task/$taskId"
    }
}
