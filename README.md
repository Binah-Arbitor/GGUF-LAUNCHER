# GGUF-LAUNCHER

A native Android application for running GGUF language models using MediaPipe framework with GPU acceleration support.

## Features

### Core Functionality
- **GGUF Model Support**: Load and run GGUF format language models on Android devices
- **MediaPipe Integration**: Leverages Google's MediaPipe Tasks GenAI for efficient model inference
- **GPU Acceleration**: Optional GPU acceleration for faster inference
- **Dark Theme UI**: LM Studio-inspired dark theme interface

### Chat Interface
- Real-time streaming responses
- Message history
- Clear chat functionality
- Auto-scroll to latest messages

### Model Management
- Browse and load GGUF models from device storage
- View current model information (name, size, status)
- Support for multiple model formats

### RAG (Retrieval-Augmented Generation)
- Add documents from device storage for context
- Automatic document embedding and indexing
- Semantic search for relevant context
- Enhanced responses with document context

### Customizable Settings
Users can customize the following parameters:
- **Temperature** (0.0 - 2.0): Controls randomness in generation
- **Top P** (0.0 - 1.0): Nucleus sampling parameter
- **Top K** (1 - 100): Top-k sampling parameter
- **Max Tokens** (128 - 8192): Maximum response length
- **Repeat Penalty** (1.0 - 2.0): Penalty for repetitive text
- **Context Length** (512 - 32768): Context window size
- **GPU Acceleration**: Enable/disable GPU usage
- **RAG**: Enable/disable retrieval-augmented generation

## Architecture

### Technology Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Inference Engine**: MediaPipe Tasks GenAI
- **Settings Storage**: DataStore
- **Async Operations**: Kotlin Coroutines & Flow

### Key Components

#### Data Layer
- `ModelSettings`: Configuration for model inference
- `ChatMessage`: Chat message representation
- `ModelInfo`: Model metadata
- `Document`: RAG document representation
- `SettingsRepository`: Persistent settings management

#### Business Logic
- `GGUFInferenceEngine`: Handles model loading and inference with MediaPipe
- `RAGEngine`: Document embedding and retrieval
- `MainViewModel`: Central state management

#### UI Layer
- `ChatScreen`: Main chat interface
- `ModelsScreen`: Model and document management
- `SettingsScreen`: Parameter customization
- `GGUFLauncherTheme`: Dark theme implementation

## Build Requirements

- Android SDK 26+ (Android 8.0 Oreo or higher)
- Android Studio Arctic Fox or later
- Gradle 8.2+
- Kotlin 1.9.10+

## Dependencies

- AndroidX Core & AppCompat
- Jetpack Compose (UI framework)
- Material 3 (Design system)
- MediaPipe Tasks GenAI 0.10.14 (Model inference)
- DataStore (Settings persistence)
- Kotlin Coroutines (Async operations)
- JVector (Vector database for RAG)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/Binah-Arbitor/GGUF-LAUNCHER.git
cd GGUF-LAUNCHER
```

2. Open the project in Android Studio

3. Build and run on your Android device or emulator

## Usage

### Loading a Model

1. Navigate to the "Models" tab
2. Tap "Load Model"
3. Select a GGUF model file from your device
4. Wait for the model to load

### Chatting

1. Navigate to the "Chat" tab
2. Type your message in the input field
3. Press send or hit enter
4. View the streaming response

### Adding Documents for RAG

1. Navigate to the "Models" tab
2. Tap "Add Documents for RAG"
3. Select text files from your device
4. Documents will be indexed automatically
5. Enable RAG in Settings to use document context

### Customizing Settings

1. Navigate to the "Settings" tab
2. Adjust sliders for generation parameters
3. Toggle GPU acceleration and RAG
4. Settings are saved automatically

## Permissions

The app requires the following permissions:
- `READ_EXTERNAL_STORAGE`: To access model and document files
- `READ_MEDIA_*`: For Android 13+ media access
- `INTERNET`: For potential future online features
- `ACCESS_NETWORK_STATE`: Network state monitoring

## GPU Acceleration

The app supports GPU acceleration through MediaPipe's built-in capabilities. Enable it in Settings for:
- Faster inference on supported devices
- Lower latency for real-time chat
- Better performance with larger models

Note: GPU acceleration availability depends on device hardware and MediaPipe support.

## RAG Implementation

The RAG (Retrieval-Augmented Generation) module:
1. Processes documents and generates embeddings
2. Stores embeddings in a vector database
3. Performs semantic search on user queries
4. Retrieves relevant document chunks
5. Augments the prompt with context
6. Generates informed responses

## Performance Tips

- Use quantized models (Q4, Q5, Q6) for better performance
- Enable GPU acceleration if available
- Adjust max tokens based on your use case
- Lower context length for faster inference
- Disable RAG if not needed to save resources

## Known Limitations

- Model loading time depends on model size and device capabilities
- Large models may require devices with sufficient RAM
- GPU acceleration availability varies by device
- RAG uses simple embeddings (consider upgrading for production)

## Future Enhancements

- Support for multiple chat sessions
- Model quantization options
- Advanced RAG with transformer-based embeddings
- Export/import chat history
- Voice input/output
- Multi-modal support (images, audio)
- Cloud model synchronization

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues.

## License

See LICENSE file for details.

## Acknowledgments

- MediaPipe team for the GenAI Tasks library
- LM Studio for UI design inspiration
- The GGUF format and llama.cpp community