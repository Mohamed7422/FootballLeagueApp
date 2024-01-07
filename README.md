# FootballLeagueApp

## Overview
FootballLeagueApp is an Android application built using MVVM-Clean Architecture and Jetpack Compose. It provides users with information on various football competitions and detailed views for each competition.

## Features
- **List of Competitions**: View a list of football competitions.
- **Competition Details**: Access detailed information about each competition.
- **Offline Support**: Ability to work offline, caching data in a local database.
- **Secure**: Protected against MITM cyberattacks and reverse engineering.

## Technical Stack
- **Architecture**: MVVM - Clean Architecture
- **UI**: Jetpack Compose
- **Asynchronous Processing**: Coroutines and Flow
- **Image Loading**: Coil
- **Local Database**: Room
- **Network Communication**: Retrofit
- **Dependency Injection**: Dagger Hilt

## Setup and Installation
To set up the development environment for FootballLeagueApp, follow these steps:
1. Clone the repository:
Save to grepper
git clone [repository URL]

markdown
Copy code
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## Usage
The app consists of two main screens:
1. **Competitions List**: Displays a list of football competitions.
2. **Competition Details**: Shows detailed information about a selected competition.

## Security
This app implements security measures to prevent MITM attacks and reverse engineering:
- SSL pinning to safeguard against MITM attacks.
- ProGuard/R8 obfuscation and minification for added security against reverse engineering.

## Unit Testing
FootballLeagueApp includes comprehensive unit tests covering:
- **Database DAO Functions**: Ensuring the Room database operations perform as expected.
- **ViewModel Test Cases**: Testing the ViewModel for successful data retrieva
