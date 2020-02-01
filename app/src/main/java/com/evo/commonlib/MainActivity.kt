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

package com.evo.commonlib

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.evo.common.view.NavigationBar
import com.evo.common.view.NavigationBarMode
import com.evo.common.view.StatusBar
import com.evo.common.view.toDp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast(view: View) {
        Toast.makeText(this, getToastText(), Toast.LENGTH_LONG).show()
    }

    fun getToastText(): String {

        /* Using Context to retrieve the heights*/
        val sBarSizePx = StatusBar.getHeight(this, null).toFloat()
        val nBarSizePx = NavigationBar.getHeight(this, null).toFloat()

        /* Using Window to retrieve the heights*/
        val sBarSizeDp = StatusBar.getHeight(this, window).toFloat().toDp(this)
        val nBarSizeDp = NavigationBar.getHeight(this, window).toFloat().toDp(this)

        val nBarMode = getNavigationBarMode(this)

        return "StatusBar = ${sBarSizePx}px | ${sBarSizeDp}dp" + "\n\n" +
                "NavigationBar = ${nBarSizePx}px | ${nBarSizeDp}dp | Mode = $nBarMode"
    }

    fun getNavigationBarMode(context: Context): String {
        val type = when (NavigationBar.getMode(context)) {
            NavigationBarMode.MODE_3BUTTON -> "3-Button"
            NavigationBarMode.MODE_2BUTTON -> "2-Button"
            NavigationBarMode.MODE_GESTURAL -> "Gestural"
            else -> "Unknown"
        }

        return "$type Navigation"
    }
}
