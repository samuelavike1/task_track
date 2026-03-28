# Overview

As a software engineer, learning mobile development is an important step in building full-stack systems that include both backend services and user-facing applications. This project focuses on developing a mobile application using modern Android technologies to strengthen my ability to design interactive user interfaces and manage application state effectively.

The application I developed is a **Task Manager mobile app** built using Kotlin and Jetpack Compose. The app allows users to create, view, update, and delete tasks through an intuitive interface. Users can add new tasks using input fields, view a list of existing tasks, mark tasks as completed, and remove tasks when they are no longer needed. The app includes multiple screens for navigation and stores data locally using a database so that tasks persist even after the app is closed.

To use the app, the user launches it to view a list of tasks. They can add a new task by navigating to the add-task screen, entering the task details, and saving it. Tasks can be updated or deleted directly from the main screen, and users can interact with the app through buttons and text inputs.

The purpose of creating this application was to learn mobile app development using Kotlin and Jetpack Compose, and to understand how user interfaces, navigation, and local data storage work in Android applications. This project also helped me gain experience with event handling, state management, and building responsive layouts for mobile devices.

[Software Demo Video](https://youtu.be/Ry-iGIpfjGo)

# Development Environment

The application was developed using **Android Studio**, which provides tools for building, testing, and running Android applications. The emulator included in Android Studio was used to test the app during development.

The programming language used is **Kotlin**, which is the official language for Android development. The app was built using **Jetpack Compose**, Android’s modern toolkit for building native user interfaces in a declarative way.

Key components and libraries used include:

- **Jetpack Compose** for building UI components
- **State management** using Compose state APIs
- **Navigation component** for handling multiple screens
- **Room Database (SQLite)** for persistent local storage of tasks

These tools allowed me to build a functional and interactive mobile application using modern Android development practices.

# Useful Websites

The following resources were helpful during development:

* https://developer.android.com/compose
* https://kotlinlang.org/docs/home.html
* https://developer.android.com/training/basics/firstapp
* https://developer.android.com/jetpack/compose/tutorial

# Future Work

There are several improvements that could be made to enhance the application:

* Implement task categories, priorities, and due dates
* Improve the user interface with better styling and animations
* Add cloud synchronization to allow tasks to be accessed across devices
* Allow users to filter and search tasks
* Add notifications and reminders for tasks