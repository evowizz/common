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

package dev.evowizz.common.demos.mosaic

import androidx.compose.runtime.Composable
import dev.evowizz.common.R
import dev.evowizz.common.mosaic.LocalMosaic
import dev.evowizz.common.mosaic.mosaicPluralStringResource
import dev.evowizz.common.mosaic.mosaicStringResource
import dev.evowizz.common.ui.components.Category
import dev.evowizz.common.ui.components.SimpleListItem

@Composable
fun MosaicDemo() {
    Category("Mosaic")

    val mosaic = LocalMosaic.current

    val mosaicText = "**bold**," + "\n" +
        "__italic__," + "\n" +
        "__**bold & italic**__," + "\n" +
        "**[__Link__](https://example.com)**"

    SimpleListItem(
        title = "Mosaic().parse(...)",
        value = mosaic.parse(mosaicText)
    )

    SimpleListItem(
        title = "Simple bold text",
        value = mosaic.parse("This is **bold** text!")
    )

    SimpleListItem(
        title = "Simple italic text",
        value = mosaic.parse("This is __italic__ text!")

    )

    SimpleListItem(
        title = "Link annotation",
        value = mosaic.parse("Visit [Google](https://google.com) for search!")

    )

    SimpleListItem(
        title = "mosaicStringResource(...)",
        value = mosaicStringResource(R.string.mosaic_demo_bold_link)
    )

    SimpleListItem(
        title = "mosaicPluralStringResource(..., count = 1)",
        value = mosaicPluralStringResource(R.plurals.mosaic_demo_files, 1, 1)
    )

    SimpleListItem(
        title = "mosaicPluralStringResource(..., count = 42)",
        value = mosaicPluralStringResource(R.plurals.mosaic_demo_files, 42, 42)
    )
}
