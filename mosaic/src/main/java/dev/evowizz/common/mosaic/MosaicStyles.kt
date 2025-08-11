/*
 * Copyright 2025
 */

package dev.evowizz.common.mosaic

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

/**
 * Styles used when building a Mosaic [androidx.compose.ui.text.AnnotatedString].
 */
data class MosaicStyles(
    val bold: SpanStyle,
    val italic: SpanStyle,
    val link: TextLinkStyles
) {
    companion object {
        val Default = MosaicStyles(
            bold = SpanStyle(fontWeight = FontWeight.Bold),
            italic = SpanStyle(fontStyle = FontStyle.Italic),
            link = TextLinkStyles(style = SpanStyle(textDecoration = TextDecoration.Underline))
        )
    }
}

