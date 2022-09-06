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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.evowizz.common.demos.DemoList
import dev.evowizz.common.demos.core.CoreDemo
import dev.evowizz.common.demos.hashing.HashingDemo
import dev.evowizz.common.demos.mosaic.MosaicDemo
import dev.evowizz.common.ui.theme.CommonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonApp() {
    CommonTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val contentPadding = WindowInsets
                .navigationBars
                .add(WindowInsets(bottom = 16.dp))
                .asPaddingValues()

            Column(modifier = Modifier.fillMaxSize()) {
                SmallTopAppBar(
                    title = { Text(text = "Common tools") },
                )

                DemoList(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = contentPadding
                ) {
                    CoreDemo()
                    HashingDemo()
                    MosaicDemo()
                }
            }

        }
    }
}