package com.gguflauncher.rag

import android.content.Context
import android.util.Log
import com.gguflauncher.data.Document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID
import kotlin.math.sqrt

class RAGEngine(private val context: Context) {
    
    private val documents = mutableListOf<Document>()
    private val embeddingDimension = 384 // Common embedding size
    
    companion object {
        private const val TAG = "RAGEngine"
    }
    
    suspend fun addDocument(file: File): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val content = file.readText()
                val embedding = generateEmbedding(content)
                
                val document = Document(
                    id = UUID.randomUUID().toString(),
                    name = file.name,
                    path = file.absolutePath,
                    content = content,
                    embedding = embedding
                )
                
                documents.add(document)
                Log.d(TAG, "Document added: ${file.name}")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Error adding document", e)
                false
            }
        }
    }
    
    suspend fun searchRelevantDocuments(query: String, topK: Int = 3): List<Document> {
        return withContext(Dispatchers.IO) {
            if (documents.isEmpty()) {
                return@withContext emptyList()
            }
            
            val queryEmbedding = generateEmbedding(query)
            
            documents.map { doc ->
                val similarity = cosineSimilarity(queryEmbedding, doc.embedding ?: FloatArray(embeddingDimension))
                doc to similarity
            }
            .sortedByDescending { it.second }
            .take(topK)
            .map { it.first }
        }
    }
    
    fun getAugmentedPrompt(originalPrompt: String, relevantDocs: List<Document>): String {
        if (relevantDocs.isEmpty()) {
            return originalPrompt
        }
        
        val context = relevantDocs.joinToString("\n\n") { doc ->
            "Document: ${doc.name}\n${doc.content.take(500)}"
        }
        
        return """
            Context from documents:
            $context
            
            User query: $originalPrompt
            
            Please answer the query based on the context provided above.
        """.trimIndent()
    }
    
    private fun generateEmbedding(text: String): FloatArray {
        // Simple embedding generation - in production, use a proper embedding model
        // This is a placeholder that creates a deterministic embedding based on text
        val embedding = FloatArray(embeddingDimension)
        val words = text.lowercase().split(Regex("\\W+"))
        
        words.forEachIndexed { index, word ->
            val hash = word.hashCode()
            val pos = (hash % embeddingDimension).let { if (it < 0) it + embeddingDimension else it }
            embedding[pos] += 1f / (index + 1)
        }
        
        // Normalize
        val norm = sqrt(embedding.sumOf { (it * it).toDouble() }).toFloat()
        if (norm > 0) {
            for (i in embedding.indices) {
                embedding[i] /= norm
            }
        }
        
        return embedding
    }
    
    private fun cosineSimilarity(a: FloatArray, b: FloatArray): Float {
        if (a.size != b.size) return 0f
        
        var dotProduct = 0f
        var normA = 0f
        var normB = 0f
        
        for (i in a.indices) {
            dotProduct += a[i] * b[i]
            normA += a[i] * a[i]
            normB += b[i] * b[i]
        }
        
        val denominator = sqrt(normA * normB)
        return if (denominator > 0) dotProduct / denominator else 0f
    }
    
    fun clearDocuments() {
        documents.clear()
    }
    
    fun getDocumentCount(): Int = documents.size
}
