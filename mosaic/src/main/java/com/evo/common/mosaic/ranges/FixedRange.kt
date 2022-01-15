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

package com.evo.common.mosaic.ranges

/**
 * FixedRange is used to simplify IntRange.
 *
 * IntRange has start, and endInclusive. However it doesn't have end (non-inclusive).
 *
 * There's also the "ReplaceRangeStartEndInclusiveWithFirstLast" inspection which asks
 * you to switch to first and last instead of start and endInclusive.
 *
 * However, switching to first would also imply switching to last, but last is also inclusive
 * so you cannot create an extension named last yourself while you could create an "end" extension.
 *
 * This class is made to fix all of those issues in this module only, it cannot be shipped outside
 * of this module as it doesn't cover all possible ranges.
 */
internal data class FixedRange(val start: Int, val end: Int, val step: Int)

internal val IntRange.fixed get() = FixedRange(start, endInclusive + 1, step)

internal val MatchResult.fixedRange get() = range.fixed
