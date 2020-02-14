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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.evo.common.os.AndroidVersion
import com.evo.common.view.NavigationBar
import com.evo.common.view.NavigationBarMode
import com.evo.common.view.StatusBar
import com.evo.common.view.toDp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textview)
        textView.text = getText()
    }

    fun getText(): String {

        /* Using Context to retrieve the heights*/
        val sBarSizePx = StatusBar.getHeight(this, null).toFloat()
        val nBarSizePx = NavigationBar.getHeight(this, null).toFloat()

        /* Using Window to retrieve the heights*/
        /* Using Float.toDp(...)*/
        val sBarSizeDp = StatusBar.getHeight(this, window).toFloat().toDp(this)

        /* Using Int.toDp(...)*/
        val nBarSizeDp = NavigationBar.getHeight(this, window).toDp(this)


        val nBarMode = getNavigationBarMode(this)

        // AndroidVersion class is marked as deprecated until the following issue is fixed:
        // https://issuetracker.google.com/issues/120255046
        val aCodename = AndroidVersion.getCodename()
        val aIsPreview = AndroidVersion.isPreview()

        return "StatusBar:\n ${sBarSizePx}px | ${sBarSizeDp}dp" + "\n\n" +
                "NavigationBar:\n${nBarSizePx}px | ${nBarSizeDp}dp | Mode = $nBarMode" + "\n\n" +
                "AndroidVersion:\n${aCodename} | isPreview = ${aIsPreview}"
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
