package com.gguflauncher.data

data class ModelSettings(
    val temperature: Float = 0.7f,
    val topP: Float = 0.9f,
    val topK: Int = 40,
    val maxTokens: Int = 2048,
    val repeatPenalty: Float = 1.1f,
    val contextLength: Int = 4096,
    val gpuAcceleration: Boolean = true,
    val enableRAG: Boolean = false
)
