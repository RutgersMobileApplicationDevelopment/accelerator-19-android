# What we covered

- Architectural patterns
  - We talked about how we can organize our code according to different architectures, and the benefits of doing so. We briefly described a few common architectures: [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller), [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter), and [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel).
  - We talked about [how we can implement MVVM on Android](https://developer.android.com/jetpack/docs/guide), using [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) and [LiveData](https://developer.android.com/topic/libraries/architecture/livedata).

- Networking
  - We described some of the most common [HTTP request methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods).
  - We talked about using APIs, and how [JSON](https://en.wikipedia.org/wiki/JSON) is commonly used as a format for transmitting data between APIs and clients.
  - We introduced the [Retrofit](https://square.github.io/retrofit/) HTTP client, and saw how we can use it alongside a library like [Gson](https://github.com/google/gson) to turn API responses into Java/Kotlin classes.
  - We talked about why it's important not to make our network calls on the same [thread](https://en.wikipedia.org/wiki/Thread_%28computing%29) that's used for the UI.

The Week5App folder contains a slightly-improved version of the app we created, with explanatory comments added.
