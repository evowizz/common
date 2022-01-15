/*
 * Copyright 2022 Dylan Roussel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evo.common.mosaic

import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.URLSpan
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.text.italic

/**
 * Class used to parse and build Mosaic text into [SpannedString]. A custom [urlSpanProvider]
 * can be provided to use an alternative to the default [URLSpan].
 */
class MosaicBuilder(
    val urlSpanProvider: URLSpanProvider = DefaultUrlSpanProvider
) {

    /**
     * Build [input] if it contains Mosaic types.
     *
     * @return parsed [input] as a [SpannedString]
     */
    fun build(input: CharSequence): SpannedString {
        val parsedMosaic = MosaicParser.parse(input)

        return buildSpannedString {
            buildChildElements(parsedMosaic.elements, this)
        }
    }

    private fun buildChildElements(elements: List<Element>, builder: SpannableStringBuilder) {
        for (child in elements) {
            buildElement(child, builder)
        }
    }

    private fun buildElement(
        element: Element,
        builder: SpannableStringBuilder
    ): SpannableStringBuilder {
        return builder.apply {

            when (element.type) {
                Element.Type.LINK -> url(element.text) { buildChildElements(element.elements, builder) }
                Element.Type.BOLD -> bold { buildChildElements(element.elements, builder) }
                Element.Type.ITALIC -> italic { buildChildElements(element.elements, builder) }
                Element.Type.TEXT -> append(element.text)
            }
        }
    }

    private fun SpannableStringBuilder.url(
        url: CharSequence,
        builderAction: SpannableStringBuilder.() -> Unit
    ) = inSpans(urlSpanProvider.provide(url.toString()), builderAction = builderAction)


    companion object {
        private val DefaultUrlSpanProvider = URLSpanProvider { URLSpan(it) }
    }
}

/**
 * Provider of an instance of URLSpan or a derived class.
 */
fun interface URLSpanProvider {
    fun provide(url: String): URLSpan
}
