/*
 * Copyright 2021 Dylan Roussel
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

package com.evo.common.hashing

import android.util.Log
import com.evo.common.extensions.toHexString
import java.security.MessageDigest

object Hashing {
    private const val TAG = "Hashing"

    /**
     * Hash the given source with the given algorithm
     *
     * @param source The string to hash
     * @param algorithm The Algorithm to use to hash source
     */
    fun hash(source: String, algorithm: Algorithm): String = hash(source.toByteArray(), algorithm)

    /**
     * Hash the given bytes with the given algorithm
     *
     * @param bytes The ByteArray to hash
     * @param algorithm The Algorithm to use to hash bytes
     */
    fun hash(bytes: ByteArray, algorithm: Algorithm): String {
        val messageDigest = try {
            MessageDigest.getInstance(algorithm.algo)
        } catch (e: Exception) {
            Log.e(TAG, "Could not create digest for algorithm: $algorithm, returning empty string.")
            return ""
        }

        return messageDigest.digest(bytes).toHexString()
    }
}

enum class Algorithm(internal val algo: String) {
    MD2("MD2"),
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512")
}