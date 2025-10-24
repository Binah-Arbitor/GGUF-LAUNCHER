# Add project specific ProGuard rules here.
-keep class com.gguflauncher.** { *; }
-keepclassmembers class com.gguflauncher.** { *; }

# MediaPipe
-keep class com.google.mediapipe.** { *; }
-keepclassmembers class com.google.mediapipe.** { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}
