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
import androidx.compose.runtime.Composable
import dev.evowizz.common.init.ApplicationContext
import dev.evowizz.common.os.AndroidVersion
import dev.evowizz.common.os.SystemProperties
import dev.evowizz.common.ui.components.Category
import dev.evowizz.common.ui.components.SimpleListItem
import dev.evowizz.common.view.NavigationBar

@Composable
fun CoreDemo() {
    Category("Core")

    // Avoid using ApplicationContext.get() in a Composable
    // This only serves as a demo
    val appContext = ApplicationContext.get()

    SimpleListItem(
        title = "NavigationBar.getMode(...)",
        value = NavigationBar.getMode(appContext).name
    )

    AndroidVersion.whenAtLeast(Build.VERSION_CODES.R) {
        SimpleListItem(
            title = "AndroidVersion.getVersionDisplay()",
            value = AndroidVersion.getVersionDisplay()
        )
    }

    SimpleListItem(
        title = "AndroidVersion.isCanary()",
        value = AndroidVersion.isCanary().toString()
    )

    SimpleListItem(
        title = "AndroidVersion.getCodename()",
        value = AndroidVersion.getCodename()
    )

    SimpleListItem(
        title = "AndroidVersion.isPreview()",
        value = AndroidVersion.isPreview().toString()
    )

    SimpleListItem(
        title = "SystemProperties.getOrElse(...)",
        value = SystemProperties.getOrElse(systemProperty, Build.UNKNOWN)
    )

    SimpleListItem(
        title = "SystemProperties.getOrNull(...)",
        value = SystemProperties.getOrNull(faultySystemProperty) ?: "foo"
    )
}

private const val systemProperty = "ro.build.id"
private const val faultySystemProperty = "ro.prop.inexistent"