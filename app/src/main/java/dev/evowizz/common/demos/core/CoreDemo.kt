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

package dev.evowizz.common.demos.core

import android.os.Build
import dev.evowizz.common.ui.components.DemoListScope
import dev.evowizz.common.init.ApplicationContext
import dev.evowizz.common.os.AndroidVersion
import dev.evowizz.common.os.SystemProperties
import dev.evowizz.common.view.NavigationBar

fun DemoListScope.CoreDemo() {
    module("Core")

    // Avoid using ApplicationContext.get() in a Composable
    // This only serves as a demo
    val appContext = ApplicationContext.get()

    demo(
        title = "NavigationBar.getMode(...)",
        value = NavigationBar.getMode(appContext).name
    )

    AndroidVersion.whenAtLeast(Build.VERSION_CODES.R) {
        demo(
            title = "AndroidVersion.getVersionDisplay()",
            value = AndroidVersion.getVersionDisplay()
        )
    }
    demo(
        title = "AndroidVersion.getCodename()",
        value = AndroidVersion.getCodename()
    )
    demo(
        title = "AndroidVersion.isPreview()",
        value = AndroidVersion.isPreview().toString()
    )

    demo(
        title = "SystemProperties.getOrElse(...)",
        value = SystemProperties.getOrElse(systemProperty, Build.UNKNOWN)
    )
    demo(
        title = "SystemProperties.getOrNull(...)",
        value = SystemProperties.getOrNull(systemProperty) ?: "foo"
    )
}

private const val systemProperty = "ro.build.id"