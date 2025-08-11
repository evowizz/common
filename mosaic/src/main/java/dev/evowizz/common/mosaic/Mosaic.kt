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

package dev.evowizz.common.mosaic

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString

/**
 * Build Mosaic text into an AnnotatedString.
 *
 * Supported types are:
 * **bold**, __italic__, and [link](https://example.com). Nesting is supported.
 */
@Stable
class Mosaic(
    private val styles: MosaicStyles = MosaicStyles.Default
) {

    /**
     * Build [input] if it contains Mosaic types.
     *
     * @return parsed [input] as an [AnnotatedString]
     */
    fun parse(input: CharSequence): AnnotatedString {
        val parsedMosaic = MosaicParser.parse(input)

        return buildAnnotatedString {
            buildChildren(parsedMosaic.elements, this)
        }
    }

    private fun buildChildren(children: List<Element>, builder: AnnotatedString.Builder) {
        for (child in children) {
            buildElement(child, builder)
        }
    }

    private fun buildElement(
        element: Element,
        builder: AnnotatedString.Builder
    ): AnnotatedString.Builder {
        return builder.apply {
            val start = length
            if (element.type != Element.Type.TEXT) {
                // All elements except TEXT may have children, so we need to build them first
                buildChildren(element.children, this)
            }

            when (element.type) {
                Element.Type.LINK -> {
                    addLink(
                        url = LinkAnnotation.Url(
                            url = element.text.toString(),
                            styles = styles.link
                        ),
                        start = start,
                        end = length
                    )
                }
                Element.Type.BOLD -> {
                    addStyle(
                        style = styles.bold,
                        start = start,
                        end = length
                    )
                }
                Element.Type.ITALIC -> {
                    addStyle(
                        style = styles.italic,
                        start = start,
                        end = length
                    )
                }
                Element.Type.TEXT -> append(element.text.toString())
            }
        }
    }

    companion object {
        /**
         * Convenience API to parse [text] using optional [styles].
         */
        fun parse(
            text: CharSequence,
            styles: MosaicStyles = MosaicStyles.Default
        ): AnnotatedString {
            return Mosaic(styles).parse(text)
        }
    }
}
