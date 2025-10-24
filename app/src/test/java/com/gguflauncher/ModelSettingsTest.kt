package com.gguflauncher

import com.gguflauncher.data.ModelSettings
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for the GGUF Launcher app.
 */
class ModelSettingsTest {
    
    @Test
    fun modelSettings_defaultValues_areCorrect() {
        val settings = ModelSettings()
        
        assertEquals(0.7f, settings.temperature, 0.01f)
        assertEquals(0.9f, settings.topP, 0.01f)
        assertEquals(40, settings.topK)
        assertEquals(2048, settings.maxTokens)
        assertEquals(1.1f, settings.repeatPenalty, 0.01f)
        assertEquals(4096, settings.contextLength)
        assertTrue(settings.gpuAcceleration)
        assertFalse(settings.enableRAG)
    }
    
    @Test
    fun modelSettings_copyWithChanges_worksCorrectly() {
        val settings = ModelSettings()
        val modifiedSettings = settings.copy(temperature = 1.0f, topP = 0.95f)
        
        assertEquals(1.0f, modifiedSettings.temperature, 0.01f)
        assertEquals(0.95f, modifiedSettings.topP, 0.01f)
        assertEquals(40, modifiedSettings.topK)
    }
}
