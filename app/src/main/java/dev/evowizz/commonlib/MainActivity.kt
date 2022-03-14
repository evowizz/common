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

package dev.evowizz.commonlib

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.evowizz.common.hashing.Algorithm
import dev.evowizz.common.hashing.Hashing
import dev.evowizz.common.init.ApplicationContext
import dev.evowizz.common.mosaic.MosaicBuilder
import dev.evowizz.common.mosaic.URLSpanProvider
import dev.evowizz.common.os.AndroidVersion
import dev.evowizz.common.os.SystemProperties
import dev.evowizz.common.view.NavigationBar
import dev.evowizz.common.view.NavigationBarMode
import dev.evowizz.common.view.toDp
import dev.evowizz.common.view.toPx
import dev.evowizz.commonlib.R

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCoreText(findViewById(R.id.coreText))
        setupHashingText(findViewById(R.id.hashingText))
        setupMosaicText(findViewById(R.id.mosaicText))
    }

    private fun setupCoreText(textView: TextView) {

        /* Using Float.toPx(...)*/
        val toPxValue = 4f.toPx(this)

        /* Using Int.toDp(...)*/
        val toDpValue = 16.toDp(this)

        val aContext = ApplicationContext.get()
        val nBarMode = getNavigationBarMode(aContext)

        val aCodename = AndroidVersion.getCodename()
        val aIsPreview = AndroidVersion.isPreview()

        val sProperty = "ro.build.id"
        val sPropertyResult = SystemProperties.getOrElse(sProperty, "Unknown")

        textView.text = "4f.toPx = ${toPxValue}px" + "\n" +
                "16.toDp = ${toDpValue}dp" + "\n\n" +
                "NavigationBar:\nMode = $nBarMode" + "\n\n" +
                "AndroidVersion:\n$aCodename | isPreview = $aIsPreview" + "\n\n" +
                "SystemProperties:\n$sProperty = $sPropertyResult"
    }

    private fun setupHashingText(textView: TextView) {
        val sha1 = Hashing.hash("Hello, World!", Algorithm.SHA1)

        textView.text = "Hashing:\nHello, World! = $sha1"
    }

    private fun setupMosaicText(textView: TextView) {
        val demoText = "Parsing:\n" +
                "**bold**," + "\n" +
                "__italic__," + "\n" +
                "__**bold & italic**__," + "\n" +
                "**[__Link__](https://example.com)**"

        with(textView) {
            movementMethod = LinkMovementMethod.getInstance()
            text = MosaicBuilder(getUrlSpanProvider()).build(demoText)
        }

    }

    fun getUrlSpanProvider() = URLSpanProvider { DemoCustomUrlSpan(it) }

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

private class DemoCustomUrlSpan(url: String) : URLSpan(url) {

    override fun onClick(widget: View) {
        super.onClick(widget)
        Log.d("DemoCustomUrlSpan", "URL Clicked: $url")
    }
}
