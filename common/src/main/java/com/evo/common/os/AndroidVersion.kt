/*
 * Copyright 2020 Dylan Roussel
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

package com.evo.common.os

import android.annotation.TargetApi
import android.os.Build

/**
 * Created by Dylan Roussel on 06/12/2019
 */

object AndroidVersion {

    /**
     * Verify if [codename] is the current preview of the device or above.
     */
    fun isPreview(codename: Char): Boolean {
        val sysCodename = getCodename()
        return isPreview() &&
                sysCodename.length == 1 &&
                sysCodename[0] >= codename &&
                sysCodename[0] <= 'Z'
    }

    fun isPreview(): Boolean {
        return Build.VERSION.PREVIEW_SDK_INT > 0
    }

    fun getCodename(): String {
        return Build.VERSION.CODENAME
    }

    @TargetApi(29)
    fun isAtLeastQ(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    @TargetApi(28)
    fun isAtLeastP(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    @TargetApi(27)
    fun isAtLeastO_MR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
    }

    @TargetApi(26)
    fun isAtLeastO(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    @TargetApi(25)
    fun isAtLeastN_MR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
    }

    @TargetApi(24)
    fun isAtLeastN(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    @TargetApi(23)
    fun isAtLeastM(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}