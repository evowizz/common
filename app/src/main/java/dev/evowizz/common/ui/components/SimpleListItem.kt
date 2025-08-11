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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.evowizz.common.ui.theme.CommonTheme

@Composable
fun SimpleListItem(
    modifier: Modifier = Modifier,
    title: String,
    secondLine: String
) = SimpleListItem(
    modifier = modifier,
    title = title,
    secondLine = AnnotatedString(secondLine)
)

@Composable
fun SimpleListItem(
    modifier: Modifier = Modifier,
    title: String,
    secondLine: AnnotatedString
) = Column(modifier = modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = secondLine,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleListItemPreview() {
    CommonTheme {
        SimpleListItem(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            title = "Foo Bar",
            secondLine = "Hello World!"
        )
    }
}
