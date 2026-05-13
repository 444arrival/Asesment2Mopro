package com.rheivalseptian8600.asessment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rheivalseptian8600.asessment2.navigation.SetupNavGraph
import com.rheivalseptian8600.asessment2.ui.theme.Asessment2Theme
import com.rheivalseptian8600.asessment2.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val isDarkMode by viewModel.isDarkMode.collectAsState()

            Asessment2Theme(darkTheme = isDarkMode) {
                SetupNavGraph()
            }
        }
    }
}