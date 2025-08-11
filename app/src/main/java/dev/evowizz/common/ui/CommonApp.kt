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

package dev.evowizz.common.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.evowizz.common.R
import dev.evowizz.common.demos.core.CoreDemo
import dev.evowizz.common.demos.hashing.HashingDemo
import dev.evowizz.common.demos.mosaic.MosaicDemo
import dev.evowizz.common.ui.components.DemoList
import dev.evowizz.common.ui.theme.CommonTheme

@Composable
fun CommonApp() {
    CommonTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CommonContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun CommonContent() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val spacerInset = WindowInsets
        .navigationBars
        .add(WindowInsets(bottom = 16.dp))

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            /**
             * We want innerPadding to have additional height for the bottom WindowInsets.
             * In other words, we want the navigation bars Window Insets + 16.dp at the bottom.
             * But because the Scaffold does not support adding additional content padding,
             * we use the bottomBar parameter to bypass this limitation.
             *
             * Internally, because the height of the bottomBar is not 0, instead of using the
             * bottom WindowInsets, the Scaffold will use the height of the bottom bar.
             * So we still need to provide the `navigationBars` WindowInsets ourselves.
             */
            Spacer(modifier = Modifier.windowInsetsBottomHeight(spacerInset))
        }
    ) { innerPadding ->

        DemoList(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding),
            contentPadding = innerPadding
        ) {
            CoreDemo()
            HashingDemo()
            MosaicDemo()
        }
    }
}