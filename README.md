# TaskManager

<p align="center">
<img width="128" height="128" alt="taskmanager_iconpng" src="https://github.com/user-attachments/assets/ad4bae33-0ae1-44ff-9a1a-0ffb32edd868" />
</p>

TaskManager is a simple and lightweight yet powerful Android application for managing shopping lists and notes with dark and light themes support. Built entirely with modern Android development tools, it provides a clean and intuitive interface to keep your tasks and ideas organized.

## Features

### 📋 Shopping Lists
- Create, rename, and delete shopping lists.
- Add items with checkboxes to each list.
- See the number of completed items and a visual progress line for every list.

### 📝 Notes
- Create, rename, and delete notes with a title and a description.
- View the last edited time for each note.
- Filter notes instantly using the search bar – searches through both titles and descriptions.
- Undo deletion for notes.

### ⚙️ Settings
- Change the color of all note titles by picking one of the predefined colors.

### ℹ️ About
- Brief description of how the app works.
- Information about the author.
- License details and a link to the source code.

## Screenshots

<table width="100%">
  <tr>
    <td width="33%" align="center"><b>Notes Screen</b></td>
    <td width="33%" align="center"><b>Shopping Lists</b></td>
    <td width="33%" align="center"><b>Settings</b></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/959a598a-e516-4025-a0bb-3adc7d0f93e5" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/f6e6d8ab-e301-40cb-8bcf-c7c31e7523a6" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/418ae22a-27a1-4eb3-a424-50aa1236330e" width="100%"></td>
  </tr>
</table>

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Dependency Injection:** Hilt
- **Local Database:** Room
- **Architecture:** MVVM
- **Minimum SDK:** 24 (Android 7.0)

## Architecture

The app follows the principles of clean architecture with a clear separation of concerns:
- **UI Layer:** Compose screens and ViewModels.
- **Data Layer:** Room DAOs and repositories.
- **DI:** Hilt modules providing database and repository instances.

## Getting Started

### Prerequisites

- Android Studio Ladybug (or later)
- JDK 17
- Android SDK 36

### Build & Run

1. Clone the repository:
   ```bash
   git clone https://github.com/iso-arkisto/TaskManager.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and let the IDE download all dependencies.
4. Run the app on an emulator or a physical device.

## Roadmap
The following features and improvements are planned for future releases. 
- [ ] “Clear completed” button to remove all checked items from a list
- [ ] Filter notes by date or creation/modification time
- [ ] Pin important lists/notes to the top
- [ ] Archive lists and notes instead of deleting permanently
- [ ] Folder organization for notes

## License

This project is licensed under the Apache License, Version 2.0 – see the LICENSE file for details.
