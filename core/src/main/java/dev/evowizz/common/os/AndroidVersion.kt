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

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi

/**
 * Created by Dylan Roussel on 06/12/2019
 */

object AndroidVersion {

    private const val T_RELEASE_OR_PREVIEW = "ro.build.version.release_or_preview_display"

    /**
     * Verify if [codename] is the current preview of the device or above.
     */
    fun isAtLeastPreview(codename: String): Boolean {
        val buildCodename = getCodename()

        return isPreview() && buildCodename >= codename
    }

    @Deprecated(
        "Use \"isAtLeastPreview(String)\" instead",
        ReplaceWith("isAtLeastPreview(codename.toString())")
    )
    fun isPreview(codename: Char): Boolean = isAtLeastPreview(codename.toString())

    fun isPreview(): Boolean = Build.VERSION.PREVIEW_SDK_INT > 0

    fun getCodename(): String = Build.VERSION.CODENAME

    @RequiresApi(Build.VERSION_CODES.R)
    fun getVersionDisplay(): String {
        // Because Android displays API 32 as `12`, this tweak allows us to manually specify
        // the real version for the API 32 which is `12L` (or 12.1)
        return when {
            isAtLeastT() -> SystemProperties.getOrElse(T_RELEASE_OR_PREVIEW, Build.UNKNOWN)
            isAtLeastS_V2() -> "12L"
            else -> Build.VERSION.RELEASE_OR_CODENAME
        }
    }

    @ChecksSdkIntAtLeast(api = 33, codename = "Tiramisu")
    fun isAtLeastT(): Boolean = isAtLeast(33) || isAtLeastPreview("Tiramisu")

    @ChecksSdkIntAtLeast(api = 32, codename = "Sv2")
    fun isAtLeastS_V2(): Boolean = isAtLeast(32) || isAtLeastPreview("Sv2")

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    fun isAtLeastS(): Boolean = isAtLeast(Build.VERSION_CODES.S)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isAtLeastR(): Boolean = isAtLeast(Build.VERSION_CODES.R)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isAtLeastQ(): Boolean = isAtLeast(Build.VERSION_CODES.Q)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    fun isAtLeastP(): Boolean = isAtLeast(Build.VERSION_CODES.P)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    fun isAtLeastO_MR1(): Boolean = isAtLeast(Build.VERSION_CODES.O_MR1)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    fun isAtLeastO(): Boolean = isAtLeast(Build.VERSION_CODES.O)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun isAtLeastN_MR1(): Boolean = isAtLeast(Build.VERSION_CODES.N_MR1)

    @ChecksSdkIntAtLeast(parameter = 0)
    fun isAtLeast(api: Int): Boolean = Build.VERSION.SDK_INT >= api

    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    inline fun whenAtLeast(api: Int, action: () -> Unit) {
        if (isAtLeast(api)) {
            action()
        }
    }
}