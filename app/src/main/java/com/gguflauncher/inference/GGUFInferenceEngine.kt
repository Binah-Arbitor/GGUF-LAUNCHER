package com.gguflauncher.inference

import android.content.Context
import android.util.Log
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class GGUFInferenceEngine(private val context: Context) {
    
    private var llmInference: LlmInference? = null
    private var isModelLoaded = false
    
    companion object {
        private const val TAG = "GGUFInferenceEngine"
    }
    
    suspend fun loadModel(modelPath: String, gpuAcceleration: Boolean = true): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Loading model from: $modelPath")
                
                // Release previous model if any
                llmInference?.close()
                
                // Configure LlmInference options
                val options = LlmInference.LlmInferenceOptions.builder()
                    .setModelPath(modelPath)
                    .setMaxTokens(2048)
                    .setTopK(40)
                    .setTemperature(0.7f)
                    .setRandomSeed(0)
                    .build()
                
                llmInference = LlmInference.createFromOptions(context, options)
                isModelLoaded = true
                Log.d(TAG, "Model loaded successfully")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Error loading model", e)
                isModelLoaded = false
                false
            }
        }
    }
    
    suspend fun generateResponse(
        prompt: String,
        temperature: Float = 0.7f,
        topP: Float = 0.9f,
        topK: Int = 40,
        maxTokens: Int = 2048,
        onTokenGenerated: (String) -> Unit = {}
    ): String {
        return withContext(Dispatchers.IO) {
            if (!isModelLoaded || llmInference == null) {
                return@withContext "Error: Model not loaded"
            }
            
            try {
                val response = StringBuilder()
                llmInference?.generateResponseAsync(prompt)?.let { result ->
                    result.forEach { partialResult ->
                        partialResult?.let {
                            response.append(it)
                            withContext(Dispatchers.Main) {
                                onTokenGenerated(it)
                            }
                        }
                    }
                }
                response.toString()
            } catch (e: Exception) {
                Log.e(TAG, "Error generating response", e)
                "Error: ${e.message}"
            }
        }
    }
    
    fun isLoaded(): Boolean = isModelLoaded
    
    fun unloadModel() {
        llmInference?.close()
        llmInference = null
        isModelLoaded = false
    }
}
