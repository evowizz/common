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
import dev.evowizz.common.os.AndroidVersion.isAtLeastBaklava
import dev.evowizz.common.os.AndroidVersion.isAtLeastPreRelease

object AndroidVersion {

    /**
     * Deprecated: use [isAtLeastPreRelease] for codename-based preview checks.
     * Only use API-level checks (e.g., [isAtLeastBaklava] or [Build.VERSION.SDK_INT]) for
     * production builds.
     */
    @Deprecated(
        message = "Use isAtLeastPreRelease(codename) instead.",
        replaceWith = ReplaceWith("isAtLeastPreRelease(codename)"),
    )
    fun isAtLeastPreview(codename: String): Boolean = isAtLeastPreRelease(codename)

    /**
     * Checks whether the device build is at least the given preview [codename].
     *
     * Use API level checks (e.g., [isAtLeastBaklava] or [Build.VERSION.SDK_INT])
     * for production feature gating; this is only useful if the build is a preview, and
     * would always return `false` for stable releases.
     */
    fun isAtLeastPreRelease(codename: String): Boolean {
        val buildCodename = getCodename().uppercase()
        if (buildCodename == "REL") return false

        val knownCodenames = getKnownCodenames()
        val targetCodename = codename.uppercase()

        if (knownCodenames.isNotEmpty()) {
            return targetCodename in knownCodenames
        }

        return buildCodename >= targetCodename
    }

    fun isPreview(): Boolean = Build.VERSION.PREVIEW_SDK_INT > 0

    fun isCanary(): Boolean = getCodename() == "CANARY"

    fun getCodename(): String = Build.VERSION.CODENAME

    @RequiresApi(Build.VERSION_CODES.R)
    fun getVersionDisplay(): String {
        // Because Android displays API 32 as `12`, this tweak allows us to manually specify
        // the real version for the API 32 which is `12L` (or 12.1)
        return when {
            isAtLeastT() -> Build.VERSION.RELEASE_OR_PREVIEW_DISPLAY
            isAtLeastS2() -> "12L"
            else -> Build.VERSION.RELEASE_OR_CODENAME
        }
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.BAKLAVA)
    fun isAtLeastBaklava(): Boolean = isAtLeast(Build.VERSION_CODES.BAKLAVA)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun isAtLeastV(): Boolean = isAtLeast(Build.VERSION_CODES.VANILLA_ICE_CREAM)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun isAtLeastU(): Boolean = isAtLeast(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    fun isAtLeastT(): Boolean = isAtLeast(Build.VERSION_CODES.TIRAMISU)

    @Suppress("FunctionName")
    @Deprecated(
        "Use isAtLeastS2() instead",
        ReplaceWith("isAtLeastS2()")
    )
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
    fun isAtLeastS_V2(): Boolean = isAtLeastS2()

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
    fun isAtLeastS2(): Boolean = isAtLeast(Build.VERSION_CODES.S_V2)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    fun isAtLeastS(): Boolean = isAtLeast(Build.VERSION_CODES.S)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isAtLeastR(): Boolean = isAtLeast(Build.VERSION_CODES.R)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isAtLeastQ(): Boolean = isAtLeast(Build.VERSION_CODES.Q)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    fun isAtLeastP(): Boolean = isAtLeast(Build.VERSION_CODES.P)

    @Suppress("FunctionName")
    @Deprecated(
        "Use isAtLeastOMR1() instead",
        ReplaceWith("isAtLeastOMR1()")
    )
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    fun isAtLeastO_MR1(): Boolean = isAtLeastOMR1()

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    fun isAtLeastOMR1(): Boolean = isAtLeast(Build.VERSION_CODES.O_MR1)

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    fun isAtLeastO(): Boolean = isAtLeast(Build.VERSION_CODES.O)

    @Suppress("FunctionName")
    @Deprecated(
        "Use isAtLeastNMR1() instead",
        ReplaceWith("isAtLeastNMR1()")
    )
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun isAtLeastN_MR1(): Boolean = isAtLeastNMR1()

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun isAtLeastNMR1(): Boolean = isAtLeast(Build.VERSION_CODES.N_MR1)

    @ChecksSdkIntAtLeast(parameter = 0)
    fun isAtLeast(api: Int): Boolean = Build.VERSION.SDK_INT >= api

    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    inline fun whenAtLeast(api: Int, action: () -> Unit) {
        if (isAtLeast(api)) action()
    }

    private const val KNOWN_CODENAMES_PROPERTY = "ro.build.version.known_codenames"

    /**
     * Returns the list of codenames known by the device, sourced from
     * `ro.build.version.known_codenames`. The list is uppercased for case-insensitive checks.
     */
    private fun getKnownCodenames(): List<String> {
        return SystemProperties.getOrNull(KNOWN_CODENAMES_PROPERTY)
            ?.split(",")
            ?.map { it.trim().uppercase() }
            ?: emptyList()
    }
}
