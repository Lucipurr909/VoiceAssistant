# VoiceAssistant

An Android app that uses facial expressions to generate speech — aimed at helping speech-impaired individuals converse more easily.

This repository contains an initial scaffold: CameraX preview, ML Kit face detection pipeline, a FaceProcessor that maps facial expression signals to phrases, and a TTS manager that speaks the generated text.

Quick start

1. Open this project in Android Studio (Arctic Fox or newer).
2. Add the following dependencies to the app module's build.gradle:

```gradle
dependencies {
    implementation "androidx.camera:camera-core:1.2.0"
    implementation "androidx.camera:camera-camera2:1.2.0"
    implementation "androidx.camera:camera-lifecycle:1.2.0"
    implementation "androidx.camera:camera-view:1.2.0"

    // ML Kit face detection
    implementation 'com.google.mlkit:face-detection:17.1.2'

    // Kotlin standard
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.8.0"
}
```

3. Grant CAMERA permission when prompted and run on a real device (camera required).

Notes and privacy

- This project handles camera input and may process facial data. For deployments, follow privacy best practices: process data locally when possible, provide clear consent, and allow users to opt-out.
- For production use with speech-impaired users, involve clinicians and accessibility experts for testing and iterate on mapping and UI.

Next steps

- Improve expression detection using MediaPipe FaceMesh or a small custom classifier for gestures.
- Add a configuration UI so users can map expressions to phrases and create profiles.
- Add predictive phrase suggestions and phrase history for faster conversations.
- Support offline, high-quality TTS voices and languages.

