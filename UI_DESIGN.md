# UI Design Guide

## Overview

GGUF-LAUNCHER features a dark theme UI inspired by LM Studio, optimized for readability and modern aesthetics.

## Color Scheme

### Primary Colors
- **Background**: `#1E1E1E` (Primary Dark)
- **Surface**: `#252525` (Surface Dark)
- **Secondary**: `#2D2D2D` (Secondary Dark)

### Accent Colors
- **Blue**: `#0E7AFF` (Primary actions, links)
- **Green**: `#00D26A` (Success states)

### Text Colors
- **Primary Text**: `#E0E0E0` (High contrast)
- **Secondary Text**: `#9E9E9E` (Lower contrast)

### Dividers
- **Divider**: `#3A3A3A`

## Screen Layouts

### 1. Chat Screen

**Components:**
- Top bar with model name and clear chat button
- Scrollable message list
- User messages aligned right (blue background)
- AI messages aligned left (dark gray background)
- Bottom input field with send button

**Features:**
- Auto-scroll to latest message
- Streaming response updates
- Message timestamps
- Clear visual distinction between user/AI

### 2. Models Screen

**Components:**
- Top bar with "Models" title
- Current model card (if loaded)
  - Model name
  - File size
  - Load status indicator
- "Load Model" button
- "Add Documents for RAG" button
- Loading indicator during model load

**Features:**
- File picker integration
- Model metadata display
- Document upload for RAG

### 3. Settings Screen

**Components:**
- Top bar with "Settings" title
- Scrollable settings list
- Section: Generation Parameters
  - Temperature slider (0.0 - 2.0)
  - Top P slider (0.0 - 1.0)
  - Top K slider (1 - 100)
  - Max Tokens slider (128 - 8192)
  - Repeat Penalty slider (1.0 - 2.0)
  - Context Length slider (512 - 32768)
- Section: Features
  - GPU Acceleration toggle
  - RAG toggle

**Features:**
- Real-time value updates
- Persistent settings
- Clear labels with current values

### 4. Bottom Navigation

**Tabs:**
1. Chat (chat icon)
2. Models (folder icon)
3. Settings (settings icon)

**Behavior:**
- Always visible
- Active tab highlighted in accent blue
- Smooth transitions

## Typography

### Styles
- **Title Large**: 22sp, SemiBold
- **Body Large**: 16sp, Normal
- **Label Small**: 11sp, Medium

### Font
- Default system font (Roboto on most devices)

## Component Design

### Buttons
- Rounded corners (8dp)
- Accent blue background for primary actions
- White text
- Elevation: 2dp

### Cards
- Rounded corners (12dp)
- Surface color background
- 16dp padding
- Subtle elevation

### Text Fields
- Outlined style
- Surface color background
- Focus: accent blue border
- 8dp corner radius

### Sliders
- Accent blue track
- White thumb
- Value displayed above slider

### Switches
- Accent blue when enabled
- Gray when disabled

## Spacing

### Standard Spacing Scale
- **xs**: 4dp
- **sm**: 8dp
- **md**: 16dp
- **lg**: 24dp
- **xl**: 32dp

### Usage
- Screen padding: 16dp
- Component spacing: 8dp-16dp
- Section spacing: 24dp

## Interactive States

### Button States
- **Default**: Accent blue background
- **Hover**: Slightly lighter blue
- **Pressed**: Darker blue
- **Disabled**: Gray with 50% opacity

### Text Field States
- **Default**: Surface background, divider border
- **Focused**: Accent blue border
- **Error**: Red border
- **Disabled**: 50% opacity

## Accessibility

### Contrast Ratios
- Primary text on background: 12:1
- Secondary text on background: 7:1
- Accent blue on background: 4.5:1

### Touch Targets
- Minimum size: 48dp × 48dp
- Recommended size: 56dp × 56dp for primary actions

### Content Descriptions
- All icons have content descriptions
- Buttons clearly labeled
- Form fields properly labeled

## Dark Theme Considerations

### Benefits
- Reduced eye strain in low light
- Better OLED battery efficiency
- Modern, professional appearance
- Matches LM Studio aesthetic

### Challenges
- Ensure sufficient contrast
- Avoid pure black backgrounds
- Use elevation appropriately
- Test on various displays

## Responsive Design

### Phone Portrait
- Single column layout
- Full-width components
- Bottom navigation

### Tablet/Landscape
- Potentially two-column layout (future)
- More compact navigation
- Larger message bubbles

## Animation

### Transitions
- Screen transitions: 300ms
- Tab changes: 200ms
- Message appearance: fade in 150ms
- Scroll to bottom: smooth 200ms

### Progress Indicators
- Circular progress for model loading
- Linear progress for document processing
- Streaming text appears character-by-character

## Best Practices

1. **Consistency**: Use Material 3 components consistently
2. **Clarity**: Clear labels and visual hierarchy
3. **Feedback**: Provide visual feedback for all actions
4. **Performance**: Smooth animations, no jank
5. **Accessibility**: Follow Android accessibility guidelines

## Future Enhancements

1. Custom theme colors
2. Light theme option
3. Adjustable text size
4. Custom fonts
5. Alternative navigation patterns
6. Tablet-optimized layouts
7. Landscape mode optimizations
