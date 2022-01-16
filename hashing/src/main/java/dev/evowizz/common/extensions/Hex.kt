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

package dev.evowizz.common.extensions

private val HEX_ARRAY = "0123456789ABCDEF".toCharArray()

fun ByteArray.toHexString(): String {
    val hexChars = CharArray(size * 2)
    for (i in indices) {
        val v = get(i).toInt() and 0xFF
        hexChars[i * 2] = HEX_ARRAY[v ushr 4]
        hexChars[i * 2 + 1] = HEX_ARRAY[v and 0x0F]
    }
    return String(hexChars)
}