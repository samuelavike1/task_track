package com.jhoy.tasktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jhoy.tasktrack.ui.TaskTrackApp
import com.jhoy.tasktrack.ui.theme.TaskTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTrackTheme {
                TaskTrackApp()
            }
        }
    }
}
