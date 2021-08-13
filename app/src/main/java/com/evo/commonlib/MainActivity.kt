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
import com.evo.common.hashing.Algorithm
import com.evo.common.hashing.Hashing
import com.evo.common.os.AndroidVersion
import com.evo.common.view.NavigationBar
import com.evo.common.view.NavigationBarMode
import com.evo.common.view.toDp
import com.evo.common.view.toPx

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textview)
        textView.text = getText()
    }

    fun getText(): String {

        /* Using Float.toPx(...)*/
        val toPxValue = 4f.toPx(this)

        /* Using Int.toDp(...)*/
        val toDpValue = 16.toDp(this)

        val nBarMode = getNavigationBarMode(this)

        val aCodename = AndroidVersion.getCodename()
        val aIsPreview = AndroidVersion.isPreview()

        val aHash = Hashing.hash("Hello, World!", Algorithm.SHA1)

        return "4f.toPx = ${toPxValue}px" + "\n" +
                "16.toDp = ${toDpValue}dp" + "\n\n" +
                "NavigationBar:\nMode = $nBarMode" + "\n\n" +
                "AndroidVersion:\n$aCodename | isPreview = $aIsPreview" + "\n\n" +
                "Hashing:\n" + "Hello, World! = $aHash"
    }

    fun getNavigationBarMode(context: Context): String {
        val type = when (NavigationBar.getMode(context)) {
            NavigationBarMode.MODE_3BUTTON -> "3-Button"
            NavigationBarMode.MODE_2BUTTON -> "2-Button"
            NavigationBarMode.MODE_GESTURAL -> "Gesture"
            else -> "Unknown"
        }

        return "$type Navigation"
    }
}
