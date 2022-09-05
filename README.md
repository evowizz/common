# Common
![Latest version](https://img.shields.io/github/v/release/evowizz/common?label=latest)

Common regroups various sets of tools to make Android development easier. Some of these tools are used within [Inware](https://play.google.com/store/apps/details?id=com.evo.inware).

## Installation
To use one of the various sets of tools, add the libraries to your Gradle configuration. You can find the latest version at the top of this README, or [here](https://github.com/evowizz/common/releases/latest).

### build.gradle.kts
```kts
implementation("dev.evowizz.common:core:$version")
implementation("dev.evowizz.common:hashing:$version")
implementation("dev.evowizz.common:mosaic:$version")
```
### build.gradle
```gradle
implementation 'dev.evowizz.common:core:$version'
implementation 'dev.evowizz.common:hashing:$version'
implementation 'dev.evowizz.common:mosaic:$version'
```

## Usage
### Core
Set of essential tools such as `AndroidVersion`, `SystemProperties` or even `NavigationBar`.
```kt
// ApplicationContext
val context = ApplicationContext.get()

// AndroidVersion
if (!AndroidVersion.isAtLeastT()) Log.d(TAG, "Who doesn't like Tiramisu?")
AndroidVersion.whenAtLeast(Build.VERSION_CODES.CUR_DEVELOPMENT) { Log.d(TAG, "Uhoh... Work in progress!") }

// SystemProperties
SystemProperties.getOrNull("ro.build.version.release")
SystemProperties.getOrThrow("ro.build.version.release")
SystemProperties.getOrElse("ro.build.version.release", Build.UNKNOWN)

// NavigationBar
val isGestural = NavigationBar.getMode(context) == NavigationBarMode.MODE_GESTURAL
```

### Hashing
Set of tools to hash a `String` or a `ByteArray` using various algorithms.
```kt
Hashing.hash("Hello World!", Algorithm.MD5)
Hashing.hash("foo bar".toByteArray(), Algorithm.SHA1)
```

### Mosaic
Set of tools to parse "Mosaic" text into a `SpannedString`. The name "Mosaic" is only used as a way to define text that is inspired from Markdown, but isn't quite Markdown either. Currently, only `**Bold**`, `__Italic__` and `[Links](https://example.org)` are supported. But they can also be mixed together.
```kt
val mosaicText = "**bold**," + "\n" +
        "__italic__," + "\n" +
        "__**bold & italic**__," + "\n" +
        "**[__Link__](https://example.com)**"
MosaicBuilder().build(mosaicText)
```
Note: You can also provide your own URLSpan by using the `urlSpanProvider` parameter on `MosaicBuilder`.

## License
This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
