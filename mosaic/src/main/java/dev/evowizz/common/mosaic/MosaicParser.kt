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

import dev.evowizz.common.mosaic.ranges.fixedRange

internal object MosaicParser {

    fun parse(input: CharSequence): Mosaic {
        val elements = mutableListOf<Element>()

        val regex = Element.Type.getMatchAnyPattern().toRegex()

        elements.addAll(findElements(input, regex))
        return Mosaic(elements)
    }

    private fun findElements(input: CharSequence, regex: Regex): Collection<Element> {
        val elements = mutableListOf<Element>()
        val iterator = regex.findAll(input).iterator()
        var lastIndex = 0

        while (iterator.hasNext()) {
            val result = iterator.next()
            val range = result.fixedRange

            // Item 0 of groupValues represent the whole match
            val mark = result.groupValues[1]
            val type = Element.Type.fromMatch(mark) ?: error("Unknown mark: $mark")

            if (lastIndex < range.start) {
                // Check what's between the lastIndex and the start of the current result
                elements.addAll(findElements(input.subSequence(lastIndex, range.start), regex))
            }

            val text: CharSequence
            val children = mutableListOf<Element>()
            when (type) {
                Element.Type.LINK -> {
                    val linkResult =
                        type.pattern.toRegex().find(mark) ?: error("Malformed link mark")

                    val linkText = linkResult.groupValues[2]
                    val linkUrl = linkResult.groupValues[3]

                    children.addAll(findChildElements(linkText, regex, iterator))

                    if (children.isNotEmpty()) {
                        iterator.skip(children.size)
                    }

                    lastIndex = range.end
                    elements.add(Element(type, linkUrl, children))
                }
                else -> {
                    val textStart = range.end
                    val textEnd = input.indexOf(type.mark, startIndex = range.end)

                    if (!iterator.hasNext() || textEnd == -1) continue // This is just text

                    text = input.substring(textStart, textEnd)
                    children.addAll(findChildElements(text, regex, iterator))

                    // Skip elements in iterator to avoid duplicates
                    if (children.isNotEmpty()) {
                        iterator.skip(children.size)
                    }

                    lastIndex = textEnd + type.mark.length
                    elements.add(Element(type, text, children))
                }
            }
        }

        if (lastIndex < input.length) {
            val text = input.subSequence(lastIndex, input.length)
            elements.add(Element(Element.Type.TEXT, text))
        }

        return elements
    }

    private fun findChildElements(
        input: CharSequence,
        regex: Regex,
        iterator: Iterator<MatchResult>
    ): Collection<Element> {
        val children = findElements(input, regex)
        val skipNumber = children.fold(0) { acc, element -> acc + element.type.skipStep }
        iterator.skip(skipNumber)

        return children
    }
}

private fun Iterator<Any>.skip(number: Int) = repeat(number) { if (hasNext()) next() }