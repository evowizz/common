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
import android.view.Window

/**
 * Created by Dylan Roussel on 14/10/2019
 */
object StatusBar {


    /**
     * Get the height in pixels of the status bar.
     *
     * If window is null, the default size of the status bar will be returned using context.
     *
     * @param context The context to get resources
     * @param window The window of the current activity
     * @return The height in pixels of the status bar
     */
    @Deprecated("Android now makes it easier to retrieve this value")
    fun getHeight(context: Context, window: Window?): Int {
        if (window != null) {
            val view = window.decorView.rootView
            if (view.rootWindowInsets != null) {
                return view.rootWindowInsets.systemWindowInsetTop
            }
        }

        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) {
            context.resources.getDimensionPixelSize(resId)
        } else 0
    }
}
