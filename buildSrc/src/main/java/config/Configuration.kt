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

package config

object Configuration {
    const val version = "2.2.0"
    const val description = "Set of small tools for Android"

    const val url = "https://github.com/evowizz/common"
    const val issueUrl = "$url/issues"
    const val gitUrl = "https://github.com/evowizz/common.git"

    const val developerId = "evowizz"
    const val developerName = "Dylan Roussel"
    const val developerEmail = "mail@evowizz.dev"

    const val minSdk = 24
    const val targetSdk = 32
}
