# API Documentation

## Core Components API Reference

### GGUFInferenceEngine

The main inference engine for loading and running GGUF models using MediaPipe.

#### Constructor

```kotlin
GGUFInferenceEngine(context: Context)
```

#### Methods

##### loadModel

```kotlin
suspend fun loadModel(
    modelPath: String,
    gpuAcceleration: Boolean = true
): Boolean
```

Loads a GGUF model from the specified path.

**Parameters:**
- `modelPath`: Absolute path to the GGUF model file
- `gpuAcceleration`: Enable GPU acceleration (default: true)

**Returns:** `Boolean` - true if model loaded successfully, false otherwise

**Example:**
```kotlin
val engine = GGUFInferenceEngine(context)
val success = engine.loadModel("/storage/emulated/0/models/llama-2-7b.gguf", true)
```

##### generateResponse

```kotlin
suspend fun generateResponse(
    prompt: String,
    temperature: Float = 0.7f,
    topP: Float = 0.9f,
    topK: Int = 40,
    maxTokens: Int = 2048,
    onTokenGenerated: (String) -> Unit = {}
): String
```

Generates a response for the given prompt with streaming support.

**Parameters:**
- `prompt`: Input prompt/question
- `temperature`: Randomness in generation (0.0-2.0)
- `topP`: Nucleus sampling parameter (0.0-1.0)
- `topK`: Top-k sampling parameter
- `maxTokens`: Maximum tokens to generate
- `onTokenGenerated`: Callback for each generated token

**Returns:** `String` - Complete generated response

**Example:**
```kotlin
val response = engine.generateResponse(
    prompt = "What is the capital of France?",
    temperature = 0.7f,
    onTokenGenerated = { token -> 
        println("Token: $token")
    }
)
```

##### isLoaded

```kotlin
fun isLoaded(): Boolean
```

Checks if a model is currently loaded.

**Returns:** `Boolean` - true if model is loaded

##### unloadModel

```kotlin
fun unloadModel()
```

Unloads the current model and frees resources.

---

### RAGEngine

Retrieval-Augmented Generation engine for document processing and context retrieval.

#### Constructor

```kotlin
RAGEngine(context: Context)
```

#### Methods

##### addDocument

```kotlin
suspend fun addDocument(file: File): Boolean
```

Adds a document to the RAG index.

**Parameters:**
- `file`: Document file to add

**Returns:** `Boolean` - true if document added successfully

**Example:**
```kotlin
val ragEngine = RAGEngine(context)
val file = File("/storage/emulated/0/Documents/guide.txt")
val success = ragEngine.addDocument(file)
```

##### searchRelevantDocuments

```kotlin
suspend fun searchRelevantDocuments(
    query: String,
    topK: Int = 3
): List<Document>
```

Searches for documents relevant to the query.

**Parameters:**
- `query`: Search query
- `topK`: Number of documents to return (default: 3)

**Returns:** `List<Document>` - Relevant documents sorted by similarity

**Example:**
```kotlin
val relevantDocs = ragEngine.searchRelevantDocuments(
    query = "How to configure the app?",
    topK = 3
)
```

##### getAugmentedPrompt

```kotlin
fun getAugmentedPrompt(
    originalPrompt: String,
    relevantDocs: List<Document>
): String
```

Creates an augmented prompt with document context.

**Parameters:**
- `originalPrompt`: Original user query
- `relevantDocs`: Relevant documents for context

**Returns:** `String` - Augmented prompt with context

**Example:**
```kotlin
val augmentedPrompt = ragEngine.getAugmentedPrompt(
    originalPrompt = "How do I configure this?",
    relevantDocs = relevantDocs
)
```

##### clearDocuments

```kotlin
fun clearDocuments()
```

Removes all documents from the index.

##### getDocumentCount

```kotlin
fun getDocumentCount(): Int
```

Returns the number of indexed documents.

---

### SettingsRepository

Manages persistent storage of app settings using DataStore.

#### Constructor

```kotlin
SettingsRepository(context: Context)
```

#### Properties

##### settingsFlow

```kotlin
val settingsFlow: Flow<ModelSettings>
```

Flow of current settings that updates reactively.

**Example:**
```kotlin
val repository = SettingsRepository(context)
repository.settingsFlow.collect { settings ->
    println("Temperature: ${settings.temperature}")
}
```

#### Methods

##### updateSettings

```kotlin
suspend fun updateSettings(settings: ModelSettings)
```

Updates and persists settings.

**Parameters:**
- `settings`: New settings to save

**Example:**
```kotlin
val newSettings = ModelSettings(
    temperature = 0.8f,
    topP = 0.95f,
    gpuAcceleration = true
)
repository.updateSettings(newSettings)
```

---

### MainViewModel

Central ViewModel for managing app state.

#### Properties

##### chatMessages

```kotlin
val chatMessages: StateFlow<List<ChatMessage>>
```

Current chat messages.

##### currentModel

```kotlin
val currentModel: StateFlow<ModelInfo?>
```

Currently loaded model information.

##### isLoading

```kotlin
val isLoading: StateFlow<Boolean>
```

Model loading state.

##### isGenerating

```kotlin
val isGenerating: StateFlow<Boolean>
```

Response generation state.

##### settings

```kotlin
val settings: StateFlow<ModelSettings>
```

Current app settings.

#### Methods

##### loadModel

```kotlin
fun loadModel(uri: Uri)
```

Loads a model from the given URI.

**Parameters:**
- `uri`: URI of the model file

##### sendMessage

```kotlin
fun sendMessage(content: String)
```

Sends a message and generates a response.

**Parameters:**
- `content`: Message content

##### clearChat

```kotlin
fun clearChat()
```

Clears all chat messages.

##### updateSettings

```kotlin
fun updateSettings(newSettings: ModelSettings)
```

Updates app settings.

**Parameters:**
- `newSettings`: New settings

##### addDocumentForRAG

```kotlin
fun addDocumentForRAG(uri: Uri)
```

Adds a document to the RAG index.

**Parameters:**
- `uri`: URI of the document file

---

## Data Models

### ModelSettings

```kotlin
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
```

### ChatMessage

```kotlin
data class ChatMessage(
    val id: String,
    val content: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)
```

### ModelInfo

```kotlin
data class ModelInfo(
    val name: String,
    val path: String,
    val size: Long,
    val isLoaded: Boolean = false
)
```

### Document

```kotlin
data class Document(
    val id: String,
    val name: String,
    val path: String,
    val content: String,
    val embedding: FloatArray? = null
)
```

---

## UI Components

### ChatScreen

```kotlin
@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    isGenerating: Boolean,
    modelName: String?,
    onSendMessage: (String) -> Unit,
    onClearChat: () -> Unit
)
```

Main chat interface composable.

### ModelsScreen

```kotlin
@Composable
fun ModelsScreen(
    currentModel: ModelInfo?,
    isLoading: Boolean,
    onLoadModel: (Uri) -> Unit,
    onAddDocuments: (Uri) -> Unit
)
```

Model management screen composable.

### SettingsScreen

```kotlin
@Composable
fun SettingsScreen(
    settings: ModelSettings,
    onSettingsChanged: (ModelSettings) -> Unit
)
```

Settings configuration screen composable.

### ChatMessageItem

```kotlin
@Composable
fun ChatMessageItem(message: ChatMessage)
```

Individual chat message display component.

### ChatInputField

```kotlin
@Composable
fun ChatInputField(
    onSendMessage: (String) -> Unit,
    enabled: Boolean = true
)
```

Chat input field with send button.

---

## Usage Examples

### Complete Chat Flow

```kotlin
// In MainActivity or composable
val viewModel: MainViewModel = viewModel()

// Observe state
val chatMessages by viewModel.chatMessages.collectAsState()
val currentModel by viewModel.currentModel.collectAsState()
val settings by viewModel.settings.collectAsState()

// Load model
viewModel.loadModel(modelUri)

// Send message
viewModel.sendMessage("Hello, how are you?")

// Update settings
viewModel.updateSettings(
    settings.copy(temperature = 0.8f)
)
```

### Direct Inference Engine Usage

```kotlin
// Create engine
val engine = GGUFInferenceEngine(context)

// Load model
val loaded = engine.loadModel(
    "/path/to/model.gguf",
    gpuAcceleration = true
)

if (loaded) {
    // Generate response with streaming
    val response = engine.generateResponse(
        prompt = "Explain quantum computing",
        temperature = 0.7f,
        maxTokens = 500,
        onTokenGenerated = { token ->
            // Update UI with each token
            updateUI(token)
        }
    )
}

// Clean up
engine.unloadModel()
```

### RAG with Custom Documents

```kotlin
val ragEngine = RAGEngine(context)

// Add documents
val doc1 = File("/path/to/doc1.txt")
val doc2 = File("/path/to/doc2.txt")

ragEngine.addDocument(doc1)
ragEngine.addDocument(doc2)

// Search and augment
val query = "What is the installation process?"
val relevantDocs = ragEngine.searchRelevantDocuments(query, topK = 2)
val augmentedPrompt = ragEngine.getAugmentedPrompt(query, relevantDocs)

// Use augmented prompt for inference
val response = engine.generateResponse(augmentedPrompt)
```

---

## Error Handling

### Common Errors

**Model Loading Failed:**
```kotlin
val success = engine.loadModel(modelPath)
if (!success) {
    // Handle error: invalid model, insufficient memory, etc.
    showError("Failed to load model")
}
```

**Generation Error:**
```kotlin
try {
    val response = engine.generateResponse(prompt)
} catch (e: Exception) {
    // Handle generation error
    Log.e("Inference", "Generation failed", e)
}
```

**RAG Document Error:**
```kotlin
val added = ragEngine.addDocument(file)
if (!added) {
    // Handle error: invalid file, parsing error, etc.
    showError("Failed to add document")
}
```

---

## Performance Considerations

### Model Loading
- Use IO dispatcher: `withContext(Dispatchers.IO)`
- Show loading indicator
- Handle cancellation

### Response Generation
- Use streaming for better UX
- Update UI progressively
- Allow cancellation

### Settings Updates
- Debounce slider changes
- Apply settings on commit, not during drag

---

## Best Practices

1. **Always check if model is loaded before generating**
2. **Use coroutines for all async operations**
3. **Observe state flows in UI layer**
4. **Clean up resources in onCleared()**
5. **Handle errors gracefully**
6. **Provide user feedback for long operations**
7. **Respect Android lifecycle**
