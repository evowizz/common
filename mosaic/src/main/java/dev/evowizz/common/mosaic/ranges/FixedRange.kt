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

package dev.evowizz.common.mosaic.ranges

/**
 * FixedRange is a simplified alternative to [IntRange].
 *
 * IntRange contains [IntRange.endInclusive] & [IntRange.last]. Bot of them are inclusive, and
 * `endInclusive` causes a warning.
 *
 * The purpose of this class is to fix these little annoyance, and it should remain internal.
 */
internal data class FixedRange(val start: Int, val end: Int, val step: Int)

internal val IntRange.fixed get() = FixedRange(start, endInclusive + 1, step)

internal val MatchResult.fixedRange get() = range.fixed
