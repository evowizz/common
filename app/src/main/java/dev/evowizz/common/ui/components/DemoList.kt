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

package dev.evowizz.common.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun DemoList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    content: DemoListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        DemoListScopeImpl(this).content()
    }
}

interface DemoListScope {

    fun module(title: String)

    fun demo(title: String, value: String)

    fun demo(title: String, value: AnnotatedString)

    fun note(isError: Boolean, note: String)
}

private class DemoListScopeImpl(val lazyListScope: LazyListScope) : DemoListScope {

    override fun module(title: String) = lazyListScope.item(key = title) {
        Category(
            modifier = Modifier
                .padding(horizontal = HorizontalPadding)
                .padding(top = VerticalPadding, bottom = VerticalPadding / 2),
            text = title
        )
    }

    override fun demo(title: String, value: String) = demo(title, AnnotatedString(value))

    override fun demo(title: String, value: AnnotatedString) = lazyListScope.item(key = title) {
        SimpleListItem(
            modifier = Modifier.padding(horizontal = HorizontalPadding, vertical = VerticalPadding),
            title = title,
            secondLine = value
        )
    }

    override fun note(isError: Boolean, note: String) =
        lazyListScope.item(key = note.hashCode().toString()) {
            CardMessage(
                modifier = Modifier.padding(horizontal = 16.dp),
                message = note,
                isError = isError
            )
        }
}

private val HorizontalPadding = 24.dp
private val VerticalPadding = 16.dp
