package com.jhoy.tasktrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.platform.LocalContext
import com.jhoy.tasktrack.ui.screens.AddTaskScreen
import com.jhoy.tasktrack.ui.screens.HomeScreen
import com.jhoy.tasktrack.ui.screens.MissingTaskScreen
import com.jhoy.tasktrack.ui.screens.TaskDetailScreen
import com.jhoy.tasktrack.model.TaskDatabase
import com.jhoy.tasktrack.viewmodel.TaskViewModel
import com.jhoy.tasktrack.viewmodel.TaskViewModelFactory

@Composable
fun TaskTrackApp() {
    val context = LocalContext.current.applicationContext
    val database = remember { TaskDatabase.getDatabase(context) }
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(database.taskDao())
    )
    val tasks by taskViewModel.tasks.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                tasks = tasks,
                onNavigateToAdd = { navController.navigate(Screen.AddTask.route) },
                onOpenTask = { taskId ->
                    navController.navigate(Screen.TaskDetail.createRoute(taskId))
                }
            )
        }

        composable(Screen.AddTask.route) {
            AddTaskScreen(
                onSaveTask = { title, description, category, dueLabel, onDone ->
                    taskViewModel.addTask(title, description, category, dueLabel) {
                        onDone()
                        navController.popBackStack()
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.TaskDetail.route,
            arguments = listOf(
                navArgument(Screen.TaskDetail.taskIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt(Screen.TaskDetail.taskIdArg)
            val task = taskId?.let { id -> tasks.find { it.id == id } }

            if (task == null) {
                MissingTaskScreen(onBack = { navController.popBackStack() })
            } else {
                TaskDetailScreen(
                    task = task,
                    onBack = { navController.popBackStack() },
                    onEditTask = { navController.navigate(Screen.EditTask.createRoute(task.id)) },
                    onToggleComplete = { onDone ->
                        taskViewModel.toggleTaskCompletion(task.id) { onDone() }
                    },
                    onDeleteTask = { onDone ->
                        taskViewModel.deleteTask(task.id) {
                            onDone()
                            navController.popBackStack()
                        }
                    }
                )
            }
        }

        composable(
            route = Screen.EditTask.route,
            arguments = listOf(
                navArgument(Screen.EditTask.taskIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt(Screen.EditTask.taskIdArg)
            val task = taskId?.let { id -> tasks.find { it.id == id } }

            if (task == null) {
                MissingTaskScreen(onBack = { navController.popBackStack() })
            } else {
                AddTaskScreen(
                    screenTitle = "Edit task",
                    actionLabel = "Update task",
                    helperText = "Adjust the task details, then save to return to the task view.",
                    initialTitle = task.title,
                    initialDescription = task.description,
                    initialCategory = task.category,
                    initialDueLabel = task.dueLabel,
                    onSaveTask = { title, description, category, dueLabel, onDone ->
                        taskViewModel.updateTask(
                            taskId = task.id,
                            title = title,
                            description = description,
                            category = category,
                            dueLabel = dueLabel
                        ) {
                            onDone()
                            navController.popBackStack()
                        }
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}
