# Frequently Asked Questions (FAQ)

## General Questions

### What is GGUF-LAUNCHER?
GGUF-LAUNCHER is a native Android application that allows you to run GGUF format language models on your Android device using Google's MediaPipe framework. It includes features like GPU acceleration, RAG (Retrieval-Augmented Generation), and customizable generation parameters.

### What is GGUF?
GGUF (GPT-Generated Unified Format) is a file format for storing language models, created by the llama.cpp project. It's optimized for efficient inference on various hardware.

### What devices are supported?
- Android 8.0 (API 26) or higher
- ARM64 (arm64-v8a) processors recommended
- ARM32 (armeabi-v7a) supported but slower
- Minimum 4GB RAM recommended
- 2GB+ free storage space

### Is an internet connection required?
No! All inference happens on-device. The app doesn't require internet connectivity after installation. Internet is only used for:
- Downloading the app
- Downloading GGUF models (externally)
- Future cloud features (optional)

### Is my data private?
Yes, completely. All processing happens on your device. No data is sent to external servers. Your conversations, models, and documents stay on your device.

## Model Questions

### Where can I get GGUF models?
Popular sources:
- **Hugging Face**: Search for "GGUF" models
- **TheBloke's repositories**: Extensive collection of quantized models
- **LocalLM**: Community-shared models
- Convert your own using llama.cpp tools

### What model sizes work best?
Depends on your device RAM:
- **4GB RAM**: 3B-7B parameter models with Q4 quantization
- **6GB RAM**: 7B-13B parameter models with Q4/Q5 quantization
- **8GB+ RAM**: 13B+ parameter models with Q5/Q6 quantization

### What are quantization levels?
Quantization reduces model size by using fewer bits per parameter:
- **Q4_0**: 4-bit, smallest, fastest, lower quality
- **Q4_K_M**: 4-bit, balanced quality/speed
- **Q5_K_M**: 5-bit, better quality, slightly slower
- **Q6_K**: 6-bit, high quality, larger size
- **Q8_0**: 8-bit, highest quality, slowest

Recommendation: Start with Q4_K_M or Q5_K_M

### Can I use multiple models?
Yes, but only one model can be loaded at a time. Loading a new model automatically unloads the previous one.

### How do I transfer models to my device?
Several methods:
1. **USB Transfer**: Connect via USB and copy to device
2. **ADB**: `adb push model.gguf /sdcard/Download/`
3. **Cloud Storage**: Download via Google Drive, Dropbox, etc.
4. **Direct Download**: Use browser to download directly to device

## Performance Questions

### Why is inference slow?
Common causes:
- Model too large for device
- GPU acceleration disabled
- High max tokens setting
- Large context length
- Other apps using resources

Solutions:
- Use smaller/more quantized models
- Enable GPU acceleration in Settings
- Reduce max tokens
- Reduce context length
- Close background apps

### What is GPU acceleration?
GPU acceleration uses your device's graphics processor to speed up inference. It can significantly improve performance on supported devices.

### Does GPU acceleration work on all devices?
Not all devices support GPU acceleration. It depends on:
- GPU hardware (Mali, Adreno, etc.)
- MediaPipe support for your GPU
- Android version

If GPU acceleration doesn't work, the app falls back to CPU.

### How much battery does it use?
Language model inference is computationally intensive and can drain battery quickly:
- Typical usage: 10-20% per hour
- With GPU acceleration: Potentially higher battery drain
- In standby: Minimal battery usage

Tips:
- Keep device plugged in for extended sessions
- Use lower power models
- Reduce max tokens

## RAG Questions

### What is RAG?
RAG (Retrieval-Augmented Generation) enhances model responses by retrieving relevant information from your documents before generating a response.

### What file formats are supported for RAG?
Currently:
- Plain text (.txt)
- Markdown (.md)
- Other text-based formats

Future support planned for:
- PDF
- Word documents
- HTML

### How many documents can I add?
Limited by device RAM. Typical limits:
- **4GB RAM**: 50-100 small documents
- **6GB RAM**: 100-200 small documents
- **8GB+ RAM**: 200+ documents

Document size also matters - larger documents use more memory.

### How does RAG improve responses?
RAG searches your documents for relevant information and includes it as context in the prompt, allowing the model to provide more accurate, informed responses based on your specific documents.

### Can I remove documents?
Currently, documents persist until app restart. Future versions will include document management features.

## Settings Questions

### What does each setting do?

**Temperature (0.0-2.0)**
- Controls randomness in generation
- Lower = more focused, deterministic
- Higher = more creative, random
- Recommended: 0.7-0.9

**Top P (0.0-1.0)**
- Nucleus sampling threshold
- Limits token selection to top probability mass
- Recommended: 0.9-0.95

**Top K (1-100)**
- Limits token selection to top K choices
- Lower = more focused
- Higher = more diverse
- Recommended: 40-50

**Max Tokens (128-8192)**
- Maximum response length
- Higher = longer responses, slower generation
- Recommended: 512-2048

**Repeat Penalty (1.0-2.0)**
- Penalty for repeating tokens/phrases
- Higher = less repetition
- Recommended: 1.1-1.2

**Context Length (512-32768)**
- Maximum context window size
- Higher = better memory, more RAM usage
- Recommended: 2048-4096

### Do settings persist between sessions?
Yes, all settings are saved using Android DataStore and persist across app restarts.

### What are good default settings?
For most use cases:
- Temperature: 0.7
- Top P: 0.9
- Top K: 40
- Max Tokens: 2048
- Repeat Penalty: 1.1
- Context Length: 4096
- GPU Acceleration: ON
- RAG: OFF (enable when needed)

## Troubleshooting

### App crashes on startup
1. Check Android version (min 8.0)
2. Clear app data
3. Reinstall app
4. Check available storage
5. Check logcat for errors

### Model loading hangs
1. Check file is valid GGUF format
2. Ensure sufficient free RAM
3. Try smaller model
4. Restart app
5. Check file permissions

### "Out of memory" errors
1. Close background apps
2. Use smaller model
3. Reduce context length
4. Reduce max tokens
5. Restart device

### Responses are gibberish
1. Check model is compatible
2. Adjust temperature (try 0.7)
3. Check max tokens isn't too low
4. Try different model
5. Check model isn't corrupted

### File picker doesn't show files
1. Grant storage permissions
2. For Android 13+: Grant "Files and Media"
3. Move files to Downloads folder
4. Use file manager to verify files exist
5. Restart app

## Feature Requests

### Can I export chat history?
Not currently, but planned for future release. Workaround: Screenshot or copy-paste.

### Will there be a light theme?
Potentially in future versions. Current focus is on dark theme optimization.

### Can I use custom API endpoints?
No, the app is designed for on-device inference only. Cloud API support may be added as an optional feature.

### Will voice input/output be supported?
Yes, planned for future releases using Android speech APIs.

### Can I fine-tune models in the app?
No, model training/fine-tuning is too resource-intensive for mobile devices. Use desktop tools for fine-tuning, then load the resulting model.

## Contributing

### How can I contribute?
- Report bugs on GitHub Issues
- Submit feature requests
- Contribute code via pull requests
- Improve documentation
- Share your experience

### Is the app open source?
Yes! Check the LICENSE file in the repository.

## Support

### Where can I get help?
- GitHub Issues: Bug reports and feature requests
- GitHub Discussions: Questions and community support
- Documentation: README.md and other docs

### How do I report a bug?
1. Check existing issues
2. Create new issue with:
   - Device model and Android version
   - App version
   - Steps to reproduce
   - Expected vs actual behavior
   - Logs if available

## Privacy & Security

### Does the app collect analytics?
Currently, no analytics are collected. All processing is local.

### Are conversations stored?
Conversations are stored in memory only and cleared when the app closes. No persistent storage of chat history (yet).

### Can others access my models/data?
No, all data is private to the app. Standard Android app sandboxing applies.

## Legal

### What license is the app under?
See the LICENSE file in the repository.

### Can I use this commercially?
Check the LICENSE file for commercial use terms.

## Updates

### How do I update the app?
- If installed via store: Update through the store
- If installed manually: Download and install new APK

### How often are updates released?
No fixed schedule. Updates released as features are completed and tested.

### Where can I see the changelog?
Check GitHub releases and commit history.
