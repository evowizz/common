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

package dev.evowizz.common.view

import android.content.Context
import android.provider.Settings

object NavigationBar {

    /**
     * Returns the mode of the navigation bar.
     *
     * @param context Context used to retrieve the [android.content.ContentResolver]
     *
     * @return One of [NavigationBarMode.MODE_3BUTTON],[NavigationBarMode.MODE_2BUTTON], [NavigationBarMode.MODE_GESTURAL] or [NavigationBarMode.MODE_UNKNOWN]
     */
    fun getMode(context: Context): NavigationBarMode {
        val mode = Settings.Secure.getInt(context.contentResolver, "navigation_mode", 0)
        return NavigationBarMode.fromInt(mode)
    }
}

enum class NavigationBarMode(private val mode: Int) {
    MODE_UNKNOWN(-1),
    MODE_3BUTTON(0),
    MODE_2BUTTON(1),
    MODE_GESTURAL(2);

    companion object {
        private val map = entries
            .associateBy(NavigationBarMode::mode)

        fun fromInt(mode: Int) = map[mode] ?: MODE_UNKNOWN
    }
}
