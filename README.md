# Movie-Buff

## API key setup
Api Key was obtained from [The Movie DB](https://developers.themoviedb.org/)
1. add `gradle.properties` into `.gitignore` file
2. Place the api key in `gradle.properties` file
```
    MOVIES_API_KEY="your pixabay api key"
```
3. Add following line in app level `build.gradle` file,
```gradle
android {
    ...
    defaultConfig {
    ...
        buildConfigField("String", "MOVIES_API_KEY", MOVIES_API_KEY)
    }
}
```
4. Use API key within code using BuildConfig
```
    val API_KEY :String = BuildConfig.MOVIES_API_KEY
 ```

## E2E testing preview

<p float="left">
    <img src="e2eTestPreview/untitled.gif" alt="E2E test" title="E2E test" width="200"/>

    <img src="e2eTestPreview/slowVersion.gif" alt="E2E test (Slow speed)" title="E2E test" width="200"/>
</p>

## License

MIT
