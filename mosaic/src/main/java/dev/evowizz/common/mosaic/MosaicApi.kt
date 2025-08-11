/*
 * Lightweight API surface for building Mosaic AnnotatedString
 */

package dev.evowizz.common.mosaic

import androidx.compose.ui.text.AnnotatedString

/**
 * Pure API to convert Mosaic text to [AnnotatedString].
 */
object Mosaic {
    fun parse(text: CharSequence, styles: MosaicStyles = MosaicStyles.Default): AnnotatedString {
        return MosaicBuilder(styles).build(text)
    }
}
