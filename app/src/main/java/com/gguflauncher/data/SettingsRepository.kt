package com.gguflauncher.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {
    
    companion object {
        private val TEMPERATURE = floatPreferencesKey("temperature")
        private val TOP_P = floatPreferencesKey("top_p")
        private val TOP_K = intPreferencesKey("top_k")
        private val MAX_TOKENS = intPreferencesKey("max_tokens")
        private val REPEAT_PENALTY = floatPreferencesKey("repeat_penalty")
        private val CONTEXT_LENGTH = intPreferencesKey("context_length")
        private val GPU_ACCELERATION = booleanPreferencesKey("gpu_acceleration")
        private val ENABLE_RAG = booleanPreferencesKey("enable_rag")
    }
    
    val settingsFlow: Flow<ModelSettings> = context.dataStore.data.map { preferences ->
        ModelSettings(
            temperature = preferences[TEMPERATURE] ?: 0.7f,
            topP = preferences[TOP_P] ?: 0.9f,
            topK = preferences[TOP_K] ?: 40,
            maxTokens = preferences[MAX_TOKENS] ?: 2048,
            repeatPenalty = preferences[REPEAT_PENALTY] ?: 1.1f,
            contextLength = preferences[CONTEXT_LENGTH] ?: 4096,
            gpuAcceleration = preferences[GPU_ACCELERATION] ?: true,
            enableRAG = preferences[ENABLE_RAG] ?: false
        )
    }
    
    suspend fun updateSettings(settings: ModelSettings) {
        context.dataStore.edit { preferences ->
            preferences[TEMPERATURE] = settings.temperature
            preferences[TOP_P] = settings.topP
            preferences[TOP_K] = settings.topK
            preferences[MAX_TOKENS] = settings.maxTokens
            preferences[REPEAT_PENALTY] = settings.repeatPenalty
            preferences[CONTEXT_LENGTH] = settings.contextLength
            preferences[GPU_ACCELERATION] = settings.gpuAcceleration
            preferences[ENABLE_RAG] = settings.enableRAG
        }
    }
}
