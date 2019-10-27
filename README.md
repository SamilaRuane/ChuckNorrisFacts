# Chuck Norris Facts

Android repository for [Chuck Norris](https://api.chucknorris.io/) API

This project is used to experiment some android libraries. 

## Building and Running

### Running from IDE

- Ensure you have Android Studio 3.5.1 or newer
- Is recommend to install Kotlinx.Serialization plugin on your IDE ([instructions](https://github.com/Kotlin/kotlinx.serialization))

### Building from CLI

To run all unit tests and build a APK, execute

```
./gradlew build
```

### Running integration tests

To run acceptance tests powered by Instrumentation + Espresso, execute

```
./gradlew connectedCheck
```
## Knowledge Stack

This project leverages on

- Kotlin
- RxJava for Threading
- Kodein for Dependency Injection
- Gson for JSON handling
- OkHttp + Retrofit for networking over HTTP

## Following the project
- [Issues](https://github.com/SamilaRuane/ChuckNorrisFacts/issues)
