# Architecture Overview

## Application Structure

GGUF-LAUNCHER follows the MVVM (Model-View-ViewModel) architecture pattern with clean separation of concerns.

```
app/
├── data/               # Data models and repositories
├── inference/          # Model inference engine
├── rag/               # RAG implementation
├── ui/                # User interface components
│   ├── components/    # Reusable UI components
│   ├── screens/       # Screen-level composables
│   └── theme/         # Theme and styling
├── viewmodel/         # ViewModels for state management
└── utils/             # Utility classes
```

## Layer Architecture

### 1. Data Layer

**Purpose**: Manage data models and persistence

**Components:**
- `ModelSettings`: Configuration data class
- `ChatMessage`: Chat message representation
- `ModelInfo`: Model metadata
- `Document`: RAG document structure
- `SettingsRepository`: DataStore-based settings persistence

**Responsibilities:**
- Define data structures
- Handle data persistence
- Expose data flows

### 2. Business Logic Layer

**Purpose**: Implement core functionality

**Components:**
- `GGUFInferenceEngine`: Model loading and inference
- `RAGEngine`: Document processing and retrieval
- `MainViewModel`: State management and coordination

**Responsibilities:**
- Model inference orchestration
- Document embedding and search
- State management
- Business rules enforcement

### 3. UI Layer

**Purpose**: Present data and handle user interaction

**Components:**
- `MainActivity`: Entry point
- `ChatScreen`: Chat interface
- `ModelsScreen`: Model management
- `SettingsScreen`: Configuration UI
- `ChatMessageItem`, `ChatInputField`: Reusable components

**Responsibilities:**
- Render UI
- Handle user input
- Observe state changes
- Navigation

## Data Flow

### 1. Model Loading Flow

```
User selects model (ModelsScreen)
    ↓
ViewModel.loadModel(uri)
    ↓
GGUFInferenceEngine.loadModel(path)
    ↓
MediaPipe LlmInference initialization
    ↓
State update: currentModel
    ↓
UI reflects loaded model
```

### 2. Message Generation Flow

```
User sends message (ChatScreen)
    ↓
ViewModel.sendMessage(content)
    ↓
[If RAG enabled] RAGEngine.searchRelevantDocuments()
    ↓
RAGEngine.getAugmentedPrompt()
    ↓
GGUFInferenceEngine.generateResponse()
    ↓
Token-by-token streaming callback
    ↓
State update: chatMessages
    ↓
UI updates with streaming response
```

### 3. Settings Update Flow

```
User modifies setting (SettingsScreen)
    ↓
ViewModel.updateSettings(newSettings)
    ↓
SettingsRepository.updateSettings()
    ↓
DataStore write
    ↓
StateFlow emission
    ↓
UI reflects new settings
```

## Key Design Decisions

### 1. Jetpack Compose for UI

**Why:**
- Modern declarative UI framework
- Better performance than XML layouts
- Easier state management
- Material 3 design system support

**Trade-offs:**
- Requires learning curve for XML developers
- Larger APK size
- Android 5.0+ required (we use 8.0+)

### 2. MediaPipe for Inference

**Why:**
- Official Google solution for on-device ML
- Built-in GPU acceleration support
- GGUF format support
- Optimized for mobile devices

**Trade-offs:**
- Limited compared to full llama.cpp
- Requires compatible models
- Less control over inference parameters

### 3. Kotlin Coroutines & Flow

**Why:**
- Natural async/await pattern
- Lifecycle-aware operations
- Structured concurrency
- Reactive state management

**Trade-offs:**
- Requires understanding of coroutines
- Potential for scope leaks if misused

### 4. DataStore for Settings

**Why:**
- Modern replacement for SharedPreferences
- Type-safe with Kotlin
- Async API
- Flow-based reactivity

**Trade-offs:**
- More complex than SharedPreferences
- Requires coroutines knowledge

## State Management

### ViewModel State

```kotlin
MainViewModel {
    - chatMessages: StateFlow<List<ChatMessage>>
    - currentModel: StateFlow<ModelInfo?>
    - isLoading: StateFlow<Boolean>
    - isGenerating: StateFlow<Boolean>
    - settings: StateFlow<ModelSettings>
}
```

### State Flow Direction

```
Repository/Engine → ViewModel → UI
    (data source)    (state)    (observer)
```

## Threading Model

### Main Thread (UI)
- UI rendering
- User interaction handling
- State observation

### IO Dispatcher
- File operations
- Model loading
- Document processing
- Network operations (future)

### Default Dispatcher
- CPU-intensive operations
- Embedding generation
- Vector similarity calculations

## Memory Management

### Model Loading
- Previous model unloaded before loading new one
- Models loaded on IO dispatcher
- Native memory managed by MediaPipe

### Chat History
- Stored in ViewModel (memory)
- Cleared on app termination
- Future: Persist to database

### RAG Documents
- In-memory storage
- Embeddings cached
- Limited by available RAM

## Security Considerations

### Permissions
- Storage permissions for model/document access
- Runtime permission requests
- Scoped storage compliance (Android 10+)

### Data Privacy
- All processing on-device
- No network communication
- Local model inference only

### Input Validation
- File type checking
- Size limits enforcement
- Path sanitization

## Performance Optimizations

### 1. Lazy Loading
- Models loaded on demand
- Documents processed asynchronously

### 2. GPU Acceleration
- Optional GPU usage via MediaPipe
- Fallback to CPU if unavailable

### 3. Streaming Responses
- Token-by-token generation
- Progressive UI updates
- Reduced perceived latency

### 4. Efficient State Updates
- StateFlow for reactive updates
- Minimal recomposition
- Key-based list rendering

## Testing Strategy

### Unit Tests
- Data models
- Utility functions
- ViewModel logic

### Integration Tests
- Repository operations
- ViewModel interactions
- Engine functionality

### UI Tests (Future)
- Screen navigation
- User interactions
- State changes

## Future Enhancements

### Planned Features
1. **Persistent Chat History**
   - Room database integration
   - Chat session management

2. **Advanced RAG**
   - Transformer-based embeddings
   - Hybrid search (keyword + semantic)
   - Document chunking strategies

3. **Model Management**
   - Download models from Hugging Face
   - Model quantization options
   - Model switching without reload

4. **Multi-modal Support**
   - Image understanding
   - Voice input/output
   - Document image processing

5. **Cloud Sync**
   - Optional cloud backup
   - Cross-device sync
   - Shared model library

### Technical Debt
- Improve error handling
- Add comprehensive logging
- Implement analytics
- Optimize memory usage
- Add offline documentation

## Dependencies

### Core
- AndroidX Core KTX
- Jetpack Compose
- Material 3
- Kotlin Coroutines

### ML/AI
- MediaPipe Tasks GenAI
- JVector (vector database)

### Storage
- DataStore

### Testing
- JUnit
- Espresso
- Compose Test

## Build Configuration

### Min SDK: 26 (Android 8.0)
- Required for MediaPipe
- Modern Android features

### Target SDK: 34 (Android 14)
- Latest features and security
- Scoped storage compliance

### ABIs: arm64-v8a, armeabi-v7a
- 64-bit ARM (primary)
- 32-bit ARM (legacy support)

## Conclusion

This architecture provides:
- Clear separation of concerns
- Testable components
- Maintainable codebase
- Scalable structure
- Modern Android practices
