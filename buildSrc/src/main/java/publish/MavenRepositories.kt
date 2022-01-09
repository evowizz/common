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

package publish

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI
import propertyOrEnv

fun RepositoryHandler.localRepository(project: Project, dirName: String) = with(project) {
    maven {
        name = "Local"
        url = uri(layout.buildDirectory.dir(dirName))
    }
}

fun RepositoryHandler.mavenCentralRepository(
    project: Project,
    username: String = project.propertyOrEnv("OSSRH_USERNAME"),
    password: String = project.propertyOrEnv("OSSRH_PASSWORD")
) = maven {
    name = "MavenCentral"
    url = URI.create("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

    credentials {
        setUsername(username)
        setPassword(password)
    }
}

fun RepositoryHandler.sonatypeSnapshotRepository(
    project: Project,
    username: String = project.propertyOrEnv("OSSRH_USERNAME"),
    password: String = project.propertyOrEnv("OSSRH_PASSWORD")
) = maven {
    name = "SonatypeSnapshot"
    url = URI.create("https://s01.oss.sonatype.org/content/repositories/snapshots/")

    credentials {
        setUsername(username)
        setPassword(password)
    }
}