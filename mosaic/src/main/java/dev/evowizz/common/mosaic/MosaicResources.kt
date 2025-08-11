/*
 * Composable convenience for Mosaic with Android string resources.
 */

package dev.evowizz.common.mosaic

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString

@Composable
fun mosaicStringResource(
    @StringRes id: Int,
    vararg formatArgs: Any,
    styles: MosaicStyles = MosaicStyles.Default
): AnnotatedString {
    val raw = stringResource(id, *formatArgs)
    return remember(id, raw, styles) { Mosaic.parse(raw, styles) }
}

@Composable
fun mosaicPluralStringResource(
    @PluralsRes id: Int,
    count: Int,
    vararg formatArgs: Any,
    styles: MosaicStyles = MosaicStyles.Default
): AnnotatedString {
    val raw = pluralStringResource(id, count, *formatArgs)
    return remember(id, count, raw, styles) { Mosaic.parse(raw, styles) }
}
