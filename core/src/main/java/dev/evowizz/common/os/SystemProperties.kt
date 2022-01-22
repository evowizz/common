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

package dev.evowizz.common.os

import android.annotation.SuppressLint

object SystemProperties {

    private const val systemPropertiesClassName = "android.os.SystemProperties"
    private const val systemPropertiesGetMethodName = "get"

    /**
     *  Returns the property attached to the given [key] or `null`
     */
    @SuppressLint("PrivateApi")
    fun getOrNull(key: String): String? {
        return try {
            val clazz = Class.forName(systemPropertiesClassName)
            val getter = clazz.getMethod(systemPropertiesGetMethodName, String::class.java)
            (getter.invoke(null, key) as String?).orNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Returns the property attached to the given [key] or throw
     */
    fun getOrThrow(key: String): String = getOrNull(key) ?: error("Could not find property \"$key\"")

    /**
     * Returns the property attached to the given [key] or the result of calling the [defaultValue] function
     */
    fun getOrElse(key: String, defaultValue: (key: String) -> String): String =
        getOrNull(key) ?: defaultValue(key)

    /**
     * Returns the property attached to the given [key] or [defaultValue]
     */
    fun getOrElse(key: String, defaultValue: String): String = getOrElse(key) { defaultValue }
}

private fun String?.orNull() = if (this == "") null else this