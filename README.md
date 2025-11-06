# QR Attendance App

A complete, self-contained Android application for QR-based attendance tracking.

## Features

- **Teacher Mode**: Generate unique QR codes for attendance sessions and view real-time student submissions
- **Student Mode**: Scan QR codes and submit attendance with name verification
- **In-Memory Storage**: No external database required - uses static MutableLiveData for data sharing
- **Real-Time Updates**: Teacher view updates automatically as students submit attendance
- **Duplicate Prevention**: Each student name can only be added once per session

## Technical Specifications

- **Language**: Java
- **UI**: XML Layouts
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34
- **Key Dependency**: ZXing Android Embedded 4.3.0 for QR code functionality

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/qrattendance/
│   │   ├── FakeDatabase.java          # In-memory data storage
│   │   ├── MainActivity.java          # Role selection screen
│   │   ├── TeacherActivity.java       # QR generation and monitoring
│   │   ├── StudentActivity.java       # QR scanning and submission
│   │   └── AttendanceListActivity.java # Attendance list display
│   ├── res/
│   │   ├── layout/                    # XML layouts for all activities
│   │   ├── values/                    # Strings, colors, themes
│   │   └── xml/                       # Backup and data extraction rules
│   └── AndroidManifest.xml            # App configuration
└── build.gradle                       # Build configuration
```

## How to Run

1. Open the project in Android Studio (Arctic Fox or newer recommended)
2. Wait for Gradle sync to complete
3. Connect an Android device (API 24+) or start an emulator
4. Click "Run" or press Shift+F10
5. Grant camera permission when prompted (required for QR scanning)

## Usage

### As a Teacher:
1. Launch the app and tap "I am a Teacher"
2. A unique QR code will be generated automatically
3. Students can scan this code to mark attendance
4. Tap "View Attendance List" to see all submitted names in real-time

### As a Student:
1. Launch the app and tap "I am a Student"
2. Tap "Scan Class QR Code" and scan the teacher's QR code
3. Enter your full name in the text field
4. Tap "Submit Attendance" to mark your attendance

## Key Implementation Details

- **No Backend Required**: All data is stored in memory using `MutableLiveData`
- **Observable Pattern**: Teacher view automatically updates when students submit
- **Modern Android APIs**: Uses ActivityResultLauncher for QR scanning
- **Permission Handling**: Camera permission declared in manifest
- **Session Management**: Each QR code contains a unique timestamp-based session ID

## Dependencies

```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.9.0'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
implementation 'com.journeyapps:zxing-android-embedded:4.3.0'
```

## Security

- No security vulnerabilities found (CodeQL scan passed)
- All dependencies verified against GitHub Advisory Database
- Camera permission properly declared
- No external network calls

## License

This project is provided as-is for educational and demonstration purposes.
