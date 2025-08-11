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

/**
 * Parsed Mosaic element tree.
 */
internal data class MosaicTree(val elements: List<Element>)

/**
 * Element holds different Mosaic types.
 */
internal data class Element(
    val type: Type,
    val text: CharSequence,
    val children: List<Element> = emptyList()
) {

    internal enum class Type(
        val mark: String = "",
        val pattern: String = "",
        val skipStep: Int = 0
    ) {
        TEXT,
        BOLD("**", "(\\*\\*)", 2),
        ITALIC("__", "(__)", 2),
        // Match [text](url) where text excludes ']' and url excludes ')',
        // so each group stops at its nearest closing delimiter.
        LINK(pattern = "(\\[([^\\]]+)]\\(([^)]+)\\))", skipStep = 1);

        companion object {

            private val values = entries
                .filterNot { it == TEXT }

            fun fromMatch(match: String): Type? = values
                .find { it.pattern.toRegex().matches(match) }

            fun getMatchAnyPattern() = values
                .joinToString(separator = "|", prefix = "(", postfix = ")") { it.pattern }
        }
    }
}
