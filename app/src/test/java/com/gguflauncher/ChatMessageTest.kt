package com.gguflauncher

import com.gguflauncher.data.ChatMessage
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for ChatMessage data class.
 */
class ChatMessageTest {
    
    @Test
    fun chatMessage_creation_isCorrect() {
        val message = ChatMessage(
            id = "test-id",
            content = "Hello, world!",
            isUser = true,
            timestamp = 123456789L
        )
        
        assertEquals("test-id", message.id)
        assertEquals("Hello, world!", message.content)
        assertTrue(message.isUser)
        assertEquals(123456789L, message.timestamp)
    }
    
    @Test
    fun chatMessage_defaultTimestamp_isSet() {
        val beforeTime = System.currentTimeMillis()
        val message = ChatMessage(
            id = "test-id",
            content = "Test message",
            isUser = false
        )
        val afterTime = System.currentTimeMillis()
        
        assertTrue(message.timestamp >= beforeTime)
        assertTrue(message.timestamp <= afterTime)
    }
}
