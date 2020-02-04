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

package com.evo.common.view

import android.content.Context
import android.provider.Settings
import android.view.Window


/**
 * Created by Dylan Roussel on 14/10/2019
 */
object NavigationBar {

    /**
     * Get the height in pixels of the navigation bar.
     *
     * If window is null, the default size of the navigation bar will be returned using context.
     *
     * @param context Context to get resources
     * @param window Window of the current activity
     * @return The height in pixels of the navigation bar
     */
    fun getHeight(context: Context, window: Window?): Int {
        if (window != null) {
            val view = window.decorView.rootView
            if (view.rootWindowInsets != null) {
                return view.rootWindowInsets.systemWindowInsetBottom
            }
        }

        val resId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resId > 0) {
            context.resources.getDimensionPixelSize(resId)
        } else 0
    }

    /**
     * Returns the mode of the navigation bar.
     *
     * @param context Context used to retrieve the [ContentResolver][android.content.ContentResolver]
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
        private val map = values().associateBy(NavigationBarMode::mode)
        fun fromInt(mode: Int) = map[mode] ?: MODE_UNKNOWN
    }
}
