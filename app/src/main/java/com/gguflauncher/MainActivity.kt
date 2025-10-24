package com.gguflauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gguflauncher.ui.screens.ChatScreen
import com.gguflauncher.ui.screens.ModelsScreen
import com.gguflauncher.ui.screens.SettingsScreen
import com.gguflauncher.ui.theme.GGUFLauncherTheme
import com.gguflauncher.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GGUFLauncherTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    var selectedTab by remember { mutableStateOf(0) }
    val chatMessages by viewModel.chatMessages.collectAsState()
    val currentModel by viewModel.currentModel.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val settings by viewModel.settings.collectAsState()
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Chat, contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Folder, contentDescription = "Models") },
                    label = { Text("Models") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> ChatScreen(
                    messages = chatMessages,
                    isGenerating = isGenerating,
                    modelName = currentModel?.name,
                    onSendMessage = viewModel::sendMessage,
                    onClearChat = viewModel::clearChat
                )
                1 -> ModelsScreen(
                    currentModel = currentModel,
                    isLoading = isLoading,
                    onLoadModel = viewModel::loadModel,
                    onAddDocuments = viewModel::addDocumentForRAG
                )
                2 -> SettingsScreen(
                    settings = settings,
                    onSettingsChanged = viewModel::updateSettings
                )
            }
        }
    }
}
