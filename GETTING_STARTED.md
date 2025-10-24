# Getting Started with GGUF-LAUNCHER

## Prerequisites

Before building the app, ensure you have:

1. **Android Studio** (Arctic Fox or later)
   - Download from: https://developer.android.com/studio
   
2. **Android SDK**
   - Minimum SDK: 26 (Android 8.0)
   - Target SDK: 34 (Android 14)
   
3. **Java Development Kit (JDK)**
   - JDK 8 or higher

## Building the App

### Option 1: Using Android Studio (Recommended)

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Binah-Arbitor/GGUF-LAUNCHER.git
   cd GGUF-LAUNCHER
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the GGUF-LAUNCHER directory
   - Click "OK"

3. **Sync Gradle:**
   - Android Studio will automatically prompt to sync Gradle
   - If not, click "File" > "Sync Project with Gradle Files"

4. **Build the project:**
   - Click "Build" > "Make Project" or press `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

5. **Run on device/emulator:**
   - Connect an Android device via USB with USB debugging enabled, or start an emulator
   - Click the "Run" button (green triangle) or press `Shift+F10`

### Option 2: Using Command Line

1. **Clone and navigate:**
   ```bash
   git clone https://github.com/Binah-Arbitor/GGUF-LAUNCHER.git
   cd GGUF-LAUNCHER
   ```

2. **Build the APK:**
   ```bash
   ./gradlew assembleDebug
   ```

3. **Install on device:**
   ```bash
   ./gradlew installDebug
   ```

4. **Build release APK:**
   ```bash
   ./gradlew assembleRelease
   ```
   The APK will be located at: `app/build/outputs/apk/release/app-release-unsigned.apk`

## First Time Setup

### 1. Grant Permissions

When you first launch the app, you'll need to grant:
- Storage permissions to access model files
- File access permissions for RAG documents

### 2. Download a GGUF Model

You'll need a GGUF format model file. Options include:

- **Hugging Face**: Download quantized models from Hugging Face
  - Example: Llama 2, Mistral, Phi models in GGUF format
  
- **TheBloke's repositories**: Many popular models quantized to GGUF
  - Search for models with "GGUF" in the name

- **Transfer via ADB:**
  ```bash
  adb push your-model.gguf /sdcard/Download/
  ```

### 3. Load Your First Model

1. Open the app
2. Navigate to the "Models" tab
3. Tap "Load Model"
4. Browse to your GGUF model file
5. Wait for the model to load (this may take a few moments)

### 4. Start Chatting

1. Navigate to the "Chat" tab
2. Type your message
3. Press send
4. Watch the response stream in real-time

## Troubleshooting

### Model Won't Load

**Issue**: "Error loading model" message appears

**Solutions:**
- Ensure the file is in GGUF format
- Check that the model size is compatible with your device's RAM
- Try a smaller quantized model (Q4, Q5)
- Enable GPU acceleration in Settings
- Restart the app

### App Crashes on Launch

**Issue**: App crashes immediately after opening

**Solutions:**
- Check Android version (minimum Android 8.0)
- Clear app data: Settings > Apps > GGUF Launcher > Storage > Clear Data
- Reinstall the app
- Check logcat for error messages

### Slow Inference

**Issue**: Responses are very slow

**Solutions:**
- Enable GPU acceleration in Settings
- Use a quantized model (Q4_K_M or Q5_K_M)
- Reduce max tokens in Settings
- Reduce context length
- Close other apps to free up RAM

### File Picker Not Working

**Issue**: Can't select model or document files

**Solutions:**
- Grant storage permissions in Android Settings
- For Android 13+, grant "Files and Media" permission
- Try using a file manager app to move files to Downloads folder
- Use ADB to push files to the device

### RAG Documents Not Working

**Issue**: Added documents but RAG doesn't seem to work

**Solutions:**
- Enable RAG in Settings
- Ensure documents are text-based (.txt, .md, etc.)
- Check document size (very large documents may cause issues)
- Restart the app after adding documents

## Performance Optimization

### Choosing the Right Model

- **For testing**: Use Q4_K_M quantized models (good balance)
- **For quality**: Use Q6_K or Q8_0 quantized models (slower but better)
- **For speed**: Use Q4_0 or Q4_K_S quantized models (faster but lower quality)

### Device Requirements

**Minimum:**
- Android 8.0+
- 4GB RAM
- 2GB free storage
- ARM64 processor

**Recommended:**
- Android 11+
- 6GB+ RAM
- 4GB+ free storage
- ARM64 with GPU support

### Settings Recommendations

**For fast responses:**
- Temperature: 0.7
- Top P: 0.9
- Max Tokens: 512-1024
- Context Length: 2048
- GPU Acceleration: ON

**For quality responses:**
- Temperature: 0.8-1.0
- Top P: 0.95
- Max Tokens: 2048-4096
- Context Length: 4096-8192
- GPU Acceleration: ON

## Advanced Usage

### Using RAG Effectively

1. Add relevant documents in the Models tab
2. Enable RAG in Settings
3. Ask questions related to your documents
4. The model will use document context in responses

### Multiple Models

To switch models:
1. Load a new model from the Models tab
2. The previous model is automatically unloaded
3. Settings persist across model changes

### Exporting Conversations

Currently, conversations are stored in memory. To save important conversations:
- Take screenshots
- Copy-paste text manually
- (Future feature: Export to file)

## Getting Help

- **Issues**: Report bugs on GitHub Issues
- **Discussions**: Ask questions in GitHub Discussions
- **Documentation**: Check the main README.md

## Next Steps

- Explore different models and quantization levels
- Try adding documents for RAG
- Experiment with different settings
- Share your experience and feedback!
