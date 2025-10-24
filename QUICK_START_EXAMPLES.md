# Quick Start Examples

## Example 1: Basic Chat

### Scenario
You want to have a simple conversation with a language model.

### Steps
1. **Load a model**
   - Go to Models tab
   - Tap "Load Model"
   - Select your GGUF file (e.g., `llama-2-7b-chat.Q4_K_M.gguf`)
   - Wait for "Model loaded successfully"

2. **Start chatting**
   - Go to Chat tab
   - Type: "Hello! Can you help me learn about Python?"
   - Press Send
   - Watch the response stream in

3. **Continue conversation**
   - Type follow-up: "What's a good first project for beginners?"
   - The model remembers context from previous messages

### Expected Result
Natural conversation with context awareness.

---

## Example 2: Customizing Response Style

### Scenario
You want more creative, varied responses.

### Steps
1. **Go to Settings**
   - Tap Settings tab

2. **Adjust parameters**
   - Temperature: 1.0 (more creative)
   - Top P: 0.95 (more diverse)
   - Max Tokens: 512 (concise responses)

3. **Test in Chat**
   - Ask: "Write a short story about a robot learning to paint"
   - Notice more creative, varied output

### For Focused, Deterministic Responses
- Temperature: 0.3
- Top P: 0.85
- Top K: 20

### For Balanced Responses
- Temperature: 0.7
- Top P: 0.9
- Top K: 40

---

## Example 3: Using RAG with Your Documents

### Scenario
You have a technical manual and want the model to answer questions based on it.

### Steps
1. **Prepare your document**
   - Create or transfer a text file to your device
   - Example: `user_manual.txt` with product documentation

2. **Add document to RAG**
   - Go to Models tab
   - Tap "Add Documents for RAG"
   - Select your document
   - Wait for confirmation

3. **Enable RAG**
   - Go to Settings tab
   - Turn on "Enable RAG" switch

4. **Ask questions**
   - Go to Chat tab
   - Ask: "How do I configure the WiFi settings?"
   - The model will use your manual to answer

### Expected Result
Responses based on your specific documentation, not general knowledge.

---

## Example 4: Optimizing for Speed

### Scenario
You need fast responses on a mid-range device.

### Steps
1. **Choose the right model**
   - Use a smaller model (3B-7B parameters)
   - Prefer Q4_K_M or Q4_K_S quantization

2. **Optimize settings**
   - Go to Settings
   - Max Tokens: 256-512
   - Context Length: 2048
   - GPU Acceleration: ON
   - Temperature: 0.7
   - RAG: OFF (unless needed)

3. **Test performance**
   - Ask short questions
   - Expect responses in 5-15 seconds

### Expected Result
Faster inference with acceptable quality.

---

## Example 5: Code Generation

### Scenario
You want help writing Python code.

### Steps
1. **Set appropriate settings**
   - Temperature: 0.3-0.5 (less random, more precise)
   - Top P: 0.9
   - Max Tokens: 1024-2048

2. **Ask specific questions**
   ```
   "Write a Python function to calculate fibonacci numbers with memoization"
   ```

3. **Request explanations**
   ```
   "Explain the code you just wrote line by line"
   ```

4. **Ask for modifications**
   ```
   "Add error handling for negative inputs"
   ```

### Expected Result
Clean, working code with explanations.

---

## Example 6: RAG for Research

### Scenario
You're researching a topic and have collected several articles.

### Steps
1. **Prepare documents**
   - Convert articles to .txt files
   - Name them clearly (e.g., `quantum_computing_intro.txt`)

2. **Add all documents**
   - Go to Models tab
   - Add each document via "Add Documents for RAG"

3. **Enable RAG in Settings**

4. **Ask research questions**
   ```
   "Summarize the main points about quantum entanglement from the documents"
   "What are the practical applications mentioned in the articles?"
   "Compare the different approaches discussed"
   ```

### Expected Result
Synthesized answers drawing from all your documents.

---

## Example 7: Multilingual Conversation

### Scenario
You want to practice a foreign language.

### Steps
1. **Load a multilingual model**
   - Models like Mistral, Llama 2, etc. support multiple languages

2. **Start conversation in target language**
   ```
   "Bonjour! Pouvez-vous m'aider à pratiquer le français?"
   ```

3. **Get corrections**
   ```
   "Please correct my French: 'Je vais au magasin hier'"
   ```

### Expected Result
Natural conversation in your target language with corrections.

---

## Example 8: Creative Writing

### Scenario
You want help brainstorming and writing a story.

### Steps
1. **Set creative settings**
   - Temperature: 0.9-1.2
   - Top P: 0.95
   - Max Tokens: 2048

2. **Start brainstorming**
   ```
   "Give me 5 unique science fiction story premises"
   ```

3. **Develop an idea**
   ```
   "Let's develop the third idea. Create a main character"
   ```

4. **Write together**
   ```
   "Write the opening paragraph of this story"
   "Now continue with the next paragraph where the character discovers..."
   ```

### Expected Result
Creative, varied story ideas and content.

---

## Example 9: Question Answering

### Scenario
Quick factual questions.

### Steps
1. **Set factual settings**
   - Temperature: 0.5
   - Top P: 0.9
   - Max Tokens: 256-512

2. **Ask direct questions**
   ```
   "What is photosynthesis?"
   "Who wrote Romeo and Juliet?"
   "Explain Newton's second law"
   ```

### Expected Result
Concise, accurate answers.

---

## Example 10: Troubleshooting with RAG

### Scenario
Debug issues using error logs.

### Steps
1. **Create error log file**
   - Copy error logs to a .txt file
   - Save as `error_log.txt`

2. **Add to RAG**
   - Models tab → Add Documents
   - Select error log

3. **Enable RAG**

4. **Ask for help**
   ```
   "What does this error mean and how can I fix it?"
   "Are there any patterns in these errors?"
   ```

### Expected Result
Insights into your specific errors.

---

## Tips for Best Results

### Model Selection
- **General chat**: Llama 2, Mistral, Phi
- **Code**: CodeLlama, WizardCoder
- **Creative writing**: Mythomax, Nous Hermes

### Prompt Engineering
- Be specific and clear
- Provide context
- Break complex tasks into steps
- Use examples when helpful

### Managing Context
- Clear chat when switching topics
- For long conversations, summarize periodically
- Keep context length appropriate for your device

### Performance Tuning
- Monitor device temperature
- Close background apps for better performance
- Use lower max tokens for faster iteration
- Enable GPU acceleration when available

---

## Common Workflows

### Research Assistant
1. Load model
2. Add relevant documents to RAG
3. Enable RAG
4. Ask synthesis questions
5. Take notes from responses

### Coding Helper
1. Load code-specialized model
2. Lower temperature (0.3-0.5)
3. Ask for code snippets
4. Request explanations
5. Iterate on solutions

### Writing Partner
1. Load creative model
2. Higher temperature (0.9-1.2)
3. Brainstorm ideas
4. Generate drafts
5. Refine with feedback

### Learning Tutor
1. Load general model
2. Balanced settings
3. Ask questions
4. Request examples
5. Test understanding

---

## Next Steps

After mastering these examples:
1. Experiment with different models
2. Try various parameter combinations
3. Build your RAG document library
4. Explore specialized use cases
5. Share your discoveries with the community

Happy chatting! 🚀
