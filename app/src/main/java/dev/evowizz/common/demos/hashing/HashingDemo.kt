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

package dev.evowizz.common.demos.hashing

import dev.evowizz.common.ui.components.DemoListScope
import dev.evowizz.common.hashing.Algorithm
import dev.evowizz.common.hashing.Hashing

fun DemoListScope.HashingDemo() {
    module("Hashing")

    demo(
        title = "Hashing.hash(string, SHA1)",
        value = Hashing.hash(OriginalString, Algorithm.SHA1)
    )
    demo(
        title = "Hashing.hash(byteArray, MD5)",
        value = Hashing.hash(OriginalString.toByteArray(), Algorithm.MD5)
    )
}

private const val OriginalString = "Hello World!"