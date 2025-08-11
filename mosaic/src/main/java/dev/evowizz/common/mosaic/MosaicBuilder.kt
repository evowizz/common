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

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

/**
 * Class used to parse and build Mosaic text into [AnnotatedString]. A custom [linkAnnotationTag]
 * can be provided to customize link annotations.
 *
 * Supported types are:
 * ```
 * **bold**
 * __italic__
 * [link](https://example.com)
 * ```
 */
class MosaicBuilder(
    private val linkAnnotationTag: String = "URL"
) {

    /**
     * Build [input] if it contains Mosaic types.
     *
     * @return parsed [input] as an [AnnotatedString]
     */
    fun build(input: CharSequence): AnnotatedString {
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
                            styles = TextLinkStyles(
                                style = SpanStyle(textDecoration = TextDecoration.Underline)
                            )
                        ),
                        start = start,
                        end = length
                    )
                }
                Element.Type.BOLD -> {
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = length
                    )
                }
                Element.Type.ITALIC -> {
                    addStyle(
                        style = SpanStyle(fontStyle = FontStyle.Italic),
                        start = start,
                        end = length
                    )
                }
                Element.Type.TEXT -> append(element.text.toString())
            }
        }
    }

}
