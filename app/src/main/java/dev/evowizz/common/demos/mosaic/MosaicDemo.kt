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

import dev.evowizz.common.mosaic.MosaicBuilder
import dev.evowizz.common.ui.components.DemoListScope

fun DemoListScope.MosaicDemo() {
    module("Mosaic")

    val builder = MosaicBuilder()

    val mosaicText = "**bold**," + "\n" +
            "__italic__," + "\n" +
            "__**bold & italic**__," + "\n" +
            "**[__Link__](https://example.com)**"

    demo(
        title = "MosaicBuilder().build(...)",
        value = builder.build(mosaicText)
    )

    demo(
        title = "Simple bold text",
        value = builder.build("This is **bold** text!")
    )

    demo(
        title = "Simple italic text", 
        value = builder.build("This is __italic__ text!")
    )

    demo(
        title = "Link annotation",
        value = builder.build("Visit [Google](https://google.com) for search!")
    )
}