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

Note: The `mosaic` artifact targets Jetpack Compose.

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
Mosaic parses lightweight, Markdown‑inspired text into a Jetpack Compose `AnnotatedString`.

- Supported marks: `**bold**`, `__italic__`, `[link](https://example.org)` (can be nested, e.g. `**[__Link__](https://example.com)**`).
- Compose‑first: produces `AnnotatedString` with `LinkAnnotation.Url` and styles you can customize.

Basic usage (Compose):
```kt
val mosaicText = "**bold**\n" +
    "__italic__\n" +
    "__**bold & italic**__\n" +
    "**[__Link__](https://example.com)**"

Text(text = Mosaic.parse(mosaicText))
```

With an instance and custom styles:
```kt
val styles = MosaicStyles(
    bold = SpanStyle(fontWeight = FontWeight.SemiBold),
    italic = MosaicStyles.Default.italic,
    link = TextLinkStyles(style = SpanStyle(textDecoration = TextDecoration.Underline))
)

val annotated = Mosaic(styles).parse("Visit [site](https://example.org)")
```

Resource helpers and remembering a parser:
```kt
// Provide a Mosaic instance app-/screen-wide, or if you need to customize styles
// `LocalMosaic` already provides a default instance using `MosaicStyles.Default`.
MosaicProvider(customStyles) {
    val mosaic = LocalMosaic.current
    Text(text = mosaic.parse("A **bold** inline value"))
    
    Text(text = mosaicStringResource(R.string.mosaic_demo_bold_link))
    Text(text = mosaicPluralStringResource(R.plurals.mosaic_demo_files, 2, 2))
}
```

Notes:
- Mosaic now targets Compose; it no longer returns a `SpannedString` nor accepts a custom `URLSpan`. Links are emitted as `LinkAnnotation.Url` with styling controlled by `MosaicStyles.link`.
- To react to link clicks, use Compose primitives (e.g., `ClickableText` and `AnnotatedString` annotations) according to your app’s needs.

## License
This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
