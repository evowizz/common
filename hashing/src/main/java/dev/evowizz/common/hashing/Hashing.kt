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

package dev.evowizz.common.hashing

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.security.MessageDigest

object Hashing {
    private const val TAG = "Hashing"
    private const val BUFFER_SIZE = 8 * 1024

    /**
     * Obtain a [MessageDigest] instance for the given [algorithm].
     * Returns null if the algorithm is not supported or an error occurs.
     */
    private fun obtainDigest(algorithm: Algorithm): MessageDigest? {
        return try {
            MessageDigest.getInstance(algorithm.algo)
        } catch (e: Exception) {
            Log.e(TAG, "Could not create digest for algorithm: ${algorithm.algo}", e)
            return null
        }
    }

    /** Compute the digest for the given [source] using [algorithm] and [charset]. */
    fun digest(source: String, algorithm: Algorithm, charset: Charset = Charsets.UTF_8): ByteArray =
        digest(source.toByteArray(charset), algorithm)

    /** Compute the digest for the given [bytes] using [algorithm]. */
    fun digest(bytes: ByteArray, algorithm: Algorithm): ByteArray {
        val messageDigest = obtainDigest(algorithm) ?: return ByteArray(0)
        return messageDigest.digest(bytes)
    }

    /**
     * Compute the digest of all bytes from the provided [InputStream] using the given [algorithm].
     * The stream is not closed by this method.
     */
    fun digest(input: InputStream, algorithm: Algorithm): ByteArray {
        val messageDigest = obtainDigest(algorithm) ?: return ByteArray(0)

        val buffer = ByteArray(BUFFER_SIZE)
        while (true) {
            val read = try {
                input.read(buffer)
            } catch (e: Exception) {
                Log.e(TAG, "Error while reading InputStream for hashing.", e)
                return ByteArray(0)
            }
            if (read <= 0) break
            messageDigest.update(buffer, 0, read)
        }
        return messageDigest.digest()
    }

    /** Compute the digest of the provided [File] using the given [algorithm]. */
    fun digest(file: File, algorithm: Algorithm): ByteArray =
        FileInputStream(file).use { fis -> digest(fis, algorithm) }

    /**
     * Compute the digest of the remaining bytes of [buffer] using [algorithm].
     * The original buffer's position and limit remain unchanged.
     */
    fun digest(buffer: ByteBuffer, algorithm: Algorithm): ByteArray {
        val messageDigest = obtainDigest(algorithm) ?: return ByteArray(0)
        // Use a read-only duplicate so the caller's buffer state is preserved
        messageDigest.update(buffer.asReadOnlyBuffer())
        return messageDigest.digest()
    }

    /** Returns a hex string of the digest for [source] using [algorithm] and [charset]. */
    fun hash(source: String, algorithm: Algorithm, charset: Charset = Charsets.UTF_8): String =
        digest(source, algorithm, charset).toHexString()

    /** Returns a hex string of the digest for [bytes] using [algorithm]. */
    fun hash(bytes: ByteArray, algorithm: Algorithm): String =
        digest(bytes, algorithm).toHexString()

    /** Returns a hex string of the digest for all bytes read from [input] using [algorithm]. */
    fun hash(input: InputStream, algorithm: Algorithm): String =
        digest(input, algorithm).toHexString()

    /** Returns a hex string of the digest for the contents of [file] using [algorithm]. */
    fun hash(file: File, algorithm: Algorithm): String =
        digest(file, algorithm).toHexString()

    /** Returns a hex string of the digest for the remaining bytes of [buffer] using [algorithm]. */
    fun hash(buffer: ByteBuffer, algorithm: Algorithm): String =
        digest(buffer, algorithm).toHexString()

    /**
     * Constant-time comparison for digest byte arrays.
     */
    fun isEqual(a: ByteArray, b: ByteArray): Boolean = MessageDigest.isEqual(a, b)

    /**
     * Constant-time comparison for hex-encoded digests (case-insensitive on input).
     * Returns false if either input is not valid hex.
     */
    fun isEqual(aHex: String, bHex: String): Boolean {
        val a = aHex.hexToByteArray()
        val b = bHex.hexToByteArray()
        return isEqual(a, b)
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
