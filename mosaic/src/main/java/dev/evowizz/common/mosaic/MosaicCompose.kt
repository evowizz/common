/*
 * Compose utilities for using Mosaic with resources and composition locals.
 */

package dev.evowizz.common.mosaic

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString

/**
 * CompositionLocal that provides the current [Mosaic] instance.
 *
 * By default, this creates a plain [Mosaic] with [MosaicStyles.Default].
 * Use [MosaicProvider] to supply a scoped instance with custom [MosaicStyles].
 * All reads of [LocalMosaic.current] within a provider scope return the same instance.
 */
val LocalMosaic = staticCompositionLocalOf { Mosaic() }

/**
 * Provides a [Mosaic] instance to descendants via [LocalMosaic].
 *
 * - Creates a single [Mosaic] instance remembered from [styles].
 * - Children can access it with `LocalMosaic.current` for repeated parsing without reallocation.
 *
 * Example:
 * ```kotlin
 * MosaicProvider(styles = MosaicStyles.Default) {
 *     val mosaic = LocalMosaic.current
 *     Text(text = mosaic.parse("**Hello**"))
 * }
 * ```
 */
@Composable
fun MosaicProvider(
    styles: MosaicStyles = MosaicStyles.Default,
    content: @Composable () -> Unit
) {
    val mosaic = remember(styles) { Mosaic(styles) }
    CompositionLocalProvider(LocalMosaic provides mosaic, content = content)
}

/**
 * Returns an [AnnotatedString] by parsing a string resource with the current [Mosaic].
 *
 * - Uses `LocalMosaic.current` (from [MosaicProvider]) for consistent parsing behavior.
 * - Memoized with `remember(id, raw, mosaic)` to avoid re-parsing unless inputs change.
 */
@Composable
fun mosaicStringResource(
    @StringRes id: Int,
    vararg formatArgs: Any
): AnnotatedString {
    val raw = stringResource(id, *formatArgs)
    val mosaic = LocalMosaic.current
    return remember(id, raw, mosaic) { mosaic.parse(raw) }
}

/**
 * Returns an [AnnotatedString] by parsing a plurals resource with the current [Mosaic].
 *
 * - Uses `LocalMosaic.current` (from [MosaicProvider]) for consistent parsing behavior.
 * - Memoized with `remember(id, count, raw, mosaic)` to avoid re-parsing unless inputs change.
 */
@Composable
fun mosaicPluralStringResource(
    @PluralsRes id: Int,
    count: Int,
    vararg formatArgs: Any
): AnnotatedString {
    val raw = pluralStringResource(id, count, *formatArgs)
    val mosaic = LocalMosaic.current
    return remember(id, count, raw, mosaic) { mosaic.parse(raw) }
}
