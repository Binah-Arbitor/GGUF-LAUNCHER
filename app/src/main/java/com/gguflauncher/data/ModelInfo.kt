package com.gguflauncher.data

data class ModelInfo(
    val name: String,
    val path: String,
    val size: Long,
    val isLoaded: Boolean = false
)
