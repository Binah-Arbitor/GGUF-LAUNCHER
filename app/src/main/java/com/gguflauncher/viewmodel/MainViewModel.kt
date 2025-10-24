package com.gguflauncher.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gguflauncher.data.*
import com.gguflauncher.inference.GGUFInferenceEngine
import com.gguflauncher.rag.RAGEngine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    private val settingsRepository = SettingsRepository(application)
    private val inferenceEngine = GGUFInferenceEngine(application)
    private val ragEngine = RAGEngine(application)
    
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()
    
    private val _currentModel = MutableStateFlow<ModelInfo?>(null)
    val currentModel: StateFlow<ModelInfo?> = _currentModel.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()
    
    val settings: StateFlow<ModelSettings> = settingsRepository.settingsFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, ModelSettings())
    
    fun loadModel(uri: Uri) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val file = File(uri.path ?: return@launch)
                val success = inferenceEngine.loadModel(
                    file.absolutePath, 
                    settings.value.gpuAcceleration
                )
                
                if (success) {
                    _currentModel.value = ModelInfo(
                        name = file.name,
                        path = file.absolutePath,
                        size = file.length(),
                        isLoaded = true
                    )
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun sendMessage(content: String) {
        if (content.isBlank() || _isGenerating.value) return
        
        val userMessage = ChatMessage(
            id = UUID.randomUUID().toString(),
            content = content,
            isUser = true
        )
        
        _chatMessages.value = _chatMessages.value + userMessage
        
        viewModelScope.launch {
            _isGenerating.value = true
            
            try {
                // Apply RAG if enabled
                val finalPrompt = if (settings.value.enableRAG) {
                    val relevantDocs = ragEngine.searchRelevantDocuments(content)
                    ragEngine.getAugmentedPrompt(content, relevantDocs)
                } else {
                    content
                }
                
                val assistantMessageId = UUID.randomUUID().toString()
                var currentResponse = ""
                
                val response = inferenceEngine.generateResponse(
                    prompt = finalPrompt,
                    temperature = settings.value.temperature,
                    topP = settings.value.topP,
                    topK = settings.value.topK,
                    maxTokens = settings.value.maxTokens,
                    onTokenGenerated = { token ->
                        currentResponse += token
                        val assistantMessage = ChatMessage(
                            id = assistantMessageId,
                            content = currentResponse,
                            isUser = false
                        )
                        
                        _chatMessages.value = _chatMessages.value
                            .filter { it.id != assistantMessageId } + assistantMessage
                    }
                )
                
                val assistantMessage = ChatMessage(
                    id = assistantMessageId,
                    content = response,
                    isUser = false
                )
                
                _chatMessages.value = _chatMessages.value
                    .filter { it.id != assistantMessageId } + assistantMessage
                    
            } finally {
                _isGenerating.value = false
            }
        }
    }
    
    fun clearChat() {
        _chatMessages.value = emptyList()
    }
    
    fun updateSettings(newSettings: ModelSettings) {
        viewModelScope.launch {
            settingsRepository.updateSettings(newSettings)
        }
    }
    
    fun addDocumentForRAG(uri: Uri) {
        viewModelScope.launch {
            try {
                val file = File(uri.path ?: return@launch)
                ragEngine.addDocument(file)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        inferenceEngine.unloadModel()
    }
}
