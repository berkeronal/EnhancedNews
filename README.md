
# EnhancedNews
![appicon](https://raw.githubusercontent.com/berkeronal/berkeronal.github.io/main/news1.png)

EnhancedNews is a basic news app. It uses a free API called [News API](https://newsapi.org). It gets data from API, and caches them in local Room DB and respects Single source of truth. This app created for [Kekod](https://www.twitch.tv/kekod_)'s Jedi Droid Challange week 4.

## Libraries and tools 🛠

<li><a href="https://developer.android.com/topic/libraries/architecture/navigation/">Navigation</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://developer.android.com/kotlin/flow/stateflow-and-sharedflow">StateFlow</a></li>
<li><a href="https://developer.android.com/topic/libraries/data-binding">Data Binding</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/room">RoomDB</a></li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/square/okhttp">OkHttp</a></li>
<li><a href="https://bumptech.github.io/glide">Glide</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>
<li><a href="https://newsapi.org/docs/">News API</a></li>

## Architecture
The app uses CleanArchitecture Use [Data-Domain-Presentation] architecture to avoid Vm's turn God ViewModels.

![Architecture](https://raw.githubusercontent.com/berkeronal/berkeronal.github.io/main/architecture.png)
