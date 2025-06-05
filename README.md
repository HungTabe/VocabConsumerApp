# VocabConsumerApp

## Project Overview

**VocabConsumerApp** is an Android application written in Java that uses a Content Resolver to query and display vocabulary data from the **TOEICVocabApp** via a Content Provider. This project serves as a learning exercise to demonstrate how to use Content Providers in Android to share data between apps securely.

The app allows users to:

- Enter a Topic ID to fetch TOEIC vocabulary from TOEICVocabApp.
- Display vocabulary (English words and their Vietnamese meanings) in a RecyclerView.
- Request and handle the custom permission (`com.example.toeicvocaapp.READ_VOCABULARY`) to ensure secure data access.

This project is part of a study module on Content Providers in Android development, helping learners understand how to share and access data across applications.

---

## Technical Specifications

- **Language:** Java  
- **IDE:** Android Studio  
- **Minimum SDK:** API 21 (Android 5.0)  
- **Target SDK:** API 34 (Android 14)  
- **Main Libraries:**
  - `androidx.appcompat:appcompat:1.7.0`
  - `androidx.core:core:1.13.1`
  - `androidx.recyclerview:recyclerview:1.3.2`

---

## Setup and Running the Project

### Requirements

- Android Studio (latest version, e.g., Koala | 2024.1.1)
- An Android device or emulator (API ≥ 21)
- The **TOEICVocabApp** project installed on the same device/emulator

### Installation Steps

1. **Clone or Download the Project:**

```bash
git clone <repository_url>
```

Or download the ZIP file from GitHub and extract it.

2. **Open the Project:**

- Launch Android Studio
- Select **Open an existing project**
- Navigate to the `VocabConsumerApp` folder

3. **Sync Gradle:**

- Click **Sync Project with Gradle Files** (elephant icon)
- Ensure `app/build.gradle` includes:

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'androidx.core:core:1.13.1'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

4. **Install TOEICVocabApp:**

- Make sure `TOEICVocabApp` is installed on the device/emulator
- It must include:
  - A Content Provider (`VocabularyProvider.java`) with `authorities="com.example.toeicvocaapp.provider"`
  - The custom permission `com.example.toeicvocaapp.READ_VOCABULARY` declared in `AndroidManifest.xml`

5. **Run the App:**

- Connect a device or launch an emulator
- Click **Run > Run 'app'**
- Grant the `READ_VOCABULARY` permission when prompted

---

## Usage Guide

### Launch the App

The app opens with:

- An `EditText` for entering a Topic ID
- A `Fetch Vocabulary` button
- A `RecyclerView` to display results

### Enter a Topic ID

- Input an integer (e.g., `1` for Business topic)
- Topic ID must exist in TOEICVocabApp’s SQLite DB

### Fetch and View Vocabulary

- Click `Fetch Vocabulary`
- Example output:
  ```
  contract - hợp đồng
  meeting - cuộc họp
  ```

### Handle Errors

- **No Topic ID:**  
  _"Please enter a Topic ID"_

- **Permission denied:**  
  _"Permission denied, please grant permission"_  
  App will request again.

---

## Project Structure

```
VocabConsumerApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/vocabconsumerapp/
│   │   │   │   ├── MainActivity.java           # Main interface, contains Content Resolver
│   │   │   │   ├── VocabularyAdapter.java      # RecyclerView adapter for vocabulary
│   │   │   │   ├── VocabularyContract.java     # URIs and column definitions
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml       # UI layout
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml             # String resources
│   ├── build.gradle                            # App-level Gradle config
├── build.gradle                                # Project-level Gradle settings
```

---

## Understanding Content Providers

### What is a Content Provider?

A Content Provider is like a librarian managing app data. It shares data securely with other apps via URIs.

### In This Demo

- **TOEICVocabApp:**  
  Content Provider (`VocabularyProvider`) shares vocabulary data.

- **VocabConsumerApp:**  
  Uses `ContentResolver` to access and display vocabulary.

---

## Key Concepts

- **Contract:**  
  `VocabularyContract.java` defines URIs and columns  
  Example URI:  
  `content://com.example.toeicvocaapp.provider/vocabulary`

- **Content Provider:**  
  `VocabularyProvider.java` in TOEICVocabApp  
  Queries SQLite DB using a helper  
  Protected with permission

- **Content Resolver:**  
  Used in `MainActivity.java`  
  Queries provider and updates RecyclerView

- **Permissions:**  
  `com.example.toeicvocaapp.READ_VOCABULARY` declared in both apps

- **MIME Type:**  
  `getType()` method defines content type (e.g., list or single item)

---

## How It Works

1. **TOEICVocabApp:**
   - Stores vocabulary in SQLite DB
   - Exposes data via `VocabularyProvider`

2. **VocabConsumerApp:**
   - User inputs Topic ID (e.g., 1)
   - Queries via URI:
     ```
     content://com.example.toeicvocaapp.provider/vocabulary/1
     ```
   - Displays list in RecyclerView
   - Secured with custom permission

---

## Real-World Examples

- **Google Contacts:** Shared with WhatsApp
- **Google Photos:** Accessed by Instagram via MediaStore
- **Google Calendar:** Shared with scheduling apps

> In this demo, TOEICVocabApp mimics apps like Quizlet, exposing data for other apps.

---

## Troubleshooting

- **Permission Denied:**
  - Ensure both apps declare the permission
  - Grant permission when prompted
  - Check Logcat for `SecurityException`

- **No Data Displayed:**
  - Check TOEICVocabApp is installed with valid data
  - Ensure Topic ID exists

- **Build Errors:**
  - Sync Gradle
  - Clean & Rebuild project
  - Ensure emulator is API ≥ 21 and has space

---

## Future Improvements

- Support multiple topic queries
- Add insert/update capabilities
- Use Material Design UI (e.g., FloatingActionButton)

---

## Acknowledgments

This project was developed as part of a learning module on Android Content Providers, inspired by real-world apps like **Quizlet** and **Duolingo**. Special thanks to Android documentation and the developer community.

---

## Contact

**Author:** TranDinhHung
**Email:** trandinhhung717@gmail.com  
**GitHub:** https://github.com/HungTabe  

---

**Happy Coding!**
