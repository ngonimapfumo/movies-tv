# TV & Film Reviews

Android app for discovering movies and TV shows, powered by the [TMDB API](https://www.themoviedb.org/).

**Get it on Google Play:**  
[https://play.google.com/store/apps/details?id=zw.co.nm.moviedb](https://play.google.com/store/apps/details?id=zw.co.nm.moviedb)

> This product uses the TMDB API but is not endorsed or certified by TMDB.

## Features

- Browse popular, trending, now playing, and upcoming titles
- Search movies, TV series, and people
- View cast, crew, ratings, release dates, and more
- See where to watch and pick your region
- Sign in with TMDB to sync watchlist, favorites, ratings, and custom lists
- Recent searches and personalized library tabs

## Requirements

- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- JDK 17
- Android SDK with `compileSdk` 37
- A device or emulator running **Android 8.0 (API 26)** or higher
- A free [TMDB API key](https://www.themoviedb.org/settings/api)

## Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/ngonimapfumo/movies-tv.git
   cd movies-tv
   ```

2. **Open the project** in Android Studio and let Gradle sync.

3. **Add your TMDB API key** to `local.properties` in the project root (this file is gitignored):

   ```properties
   sdk.dir=/path/to/Android/sdk
   apiKey="YOUR_TMDB_API_KEY"
   ```

   Android Studio usually creates `sdk.dir` for you. You only need to add `apiKey`.

4. **Firebase** — the project includes `app/google-services.json` for Analytics/Crashlytics. If you fork the app for your own package name, replace it with a config from your [Firebase Console](https://console.firebase.google.com/) project.

## Run

### Android Studio

1. Select a device or start an emulator.
2. Choose the **debug** build variant.
3. Click **Run** (▶) or press `Ctrl+R` / `⇧⌘R`.

### Command line

```bash
./gradlew :app:installDebug
```

Or build an APK:

```bash
./gradlew :app:assembleDebug
```

The debug APK is written to:

`app/build/outputs/apk/debug/app-debug.apk`

## Tech stack

- Kotlin, Android Views (+ some Compose)
- MVVM, Retrofit, Coroutines
- Hilt, Picasso
- Firebase Analytics & Crashlytics
- Material Design

## Package & version

| | |
|---|---|
| Application ID | `zw.co.nm.moviedb` |
| Min SDK | 26 |
| Target / Compile SDK | 37 |
| Current version | see `versionName` in `app/build.gradle` |

## Privacy & attribution

- Movie and TV data comes from [The Movie Database (TMDB)](https://www.themoviedb.org/).
- Account features (watchlist, favorites, lists, ratings) require signing in with a TMDB account.
- See the Play Store listing for the published [Data safety](https://play.google.com/store/apps/details?id=zw.co.nm.moviedb) declaration.
