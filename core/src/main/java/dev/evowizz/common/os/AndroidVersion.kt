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

/**
 * Created by Dylan Roussel on 06/12/2019
 */

object AndroidVersion {

    /**
     * Verify if [codename] is the current preview of the device or above.
     */
    fun isPreview(codename: Char): Boolean {
        val sysCodename = getCodename()
        return isPreview() && sysCodename.isNotEmpty() && sysCodename[0] >= codename
    }

    fun isPreview(): Boolean = Build.VERSION.PREVIEW_SDK_INT > 0

    fun getCodename(): String = Build.VERSION.CODENAME

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    fun isAtLeastS(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isAtLeastR(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isAtLeastQ(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    fun isAtLeastP(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    fun isAtLeastO_MR1(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    fun isAtLeastO(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun isAtLeastN_MR1(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
}