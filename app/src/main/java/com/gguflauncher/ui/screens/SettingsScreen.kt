package com.gguflauncher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gguflauncher.data.ModelSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settings: ModelSettings,
    onSettingsChanged: (ModelSettings) -> Unit
) {
    var localSettings by remember(settings) { mutableStateOf(settings) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Generation Parameters",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Temperature
            SettingSlider(
                label = "Temperature",
                value = localSettings.temperature,
                onValueChange = { 
                    localSettings = localSettings.copy(temperature = it)
                    onSettingsChanged(localSettings)
                },
                valueRange = 0f..2f,
                steps = 19
            )
            
            // Top P
            SettingSlider(
                label = "Top P",
                value = localSettings.topP,
                onValueChange = { 
                    localSettings = localSettings.copy(topP = it)
                    onSettingsChanged(localSettings)
                },
                valueRange = 0f..1f,
                steps = 19
            )
            
            // Top K
            Text(
                text = "Top K: ${localSettings.topK}",
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = localSettings.topK.toFloat(),
                onValueChange = { 
                    localSettings = localSettings.copy(topK = it.toInt())
                    onSettingsChanged(localSettings)
                },
                valueRange = 1f..100f,
                steps = 98
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Max Tokens
            Text(
                text = "Max Tokens: ${localSettings.maxTokens}",
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = localSettings.maxTokens.toFloat(),
                onValueChange = { 
                    localSettings = localSettings.copy(maxTokens = it.toInt())
                    onSettingsChanged(localSettings)
                },
                valueRange = 128f..8192f,
                steps = 62
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Repeat Penalty
            SettingSlider(
                label = "Repeat Penalty",
                value = localSettings.repeatPenalty,
                onValueChange = { 
                    localSettings = localSettings.copy(repeatPenalty = it)
                    onSettingsChanged(localSettings)
                },
                valueRange = 1f..2f,
                steps = 19
            )
            
            // Context Length
            Text(
                text = "Context Length: ${localSettings.contextLength}",
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = localSettings.contextLength.toFloat(),
                onValueChange = { 
                    localSettings = localSettings.copy(contextLength = it.toInt())
                    onSettingsChanged(localSettings)
                },
                valueRange = 512f..32768f,
                steps = 30
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // GPU Acceleration
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "GPU Acceleration",
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = localSettings.gpuAcceleration,
                    onCheckedChange = { 
                        localSettings = localSettings.copy(gpuAcceleration = it)
                        onSettingsChanged(localSettings)
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Enable RAG
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Enable RAG",
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = localSettings.enableRAG,
                    onCheckedChange = { 
                        localSettings = localSettings.copy(enableRAG = it)
                        onSettingsChanged(localSettings)
                    }
                )
            }
        }
    }
}

@Composable
fun SettingSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int
) {
    Text(
        text = "$label: %.2f".format(value),
        style = MaterialTheme.typography.bodyLarge
    )
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        steps = steps
    )
    Spacer(modifier = Modifier.height(8.dp))
}
