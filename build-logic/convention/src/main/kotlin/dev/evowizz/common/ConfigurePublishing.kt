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

import dev.evowizz.common.config.Configuration
import dev.evowizz.common.utils.localRepository
import dev.evowizz.common.utils.mavenCentralRepository
import dev.evowizz.common.utils.sonatypeSnapshotRepository
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension
import java.util.Locale

internal fun Project.configurePublishing(
    publishing: PublishingExtension,
    signing: SigningExtension
) {
    afterEvaluate {
        with(publishing) {
            project.group = "dev.evowizz.common"
            project.version = Configuration.version

            publications {

                create<MavenPublication>("release") {
                    from(components["release"])

                    pom {
                        name.set(artifactId.capitalize(Locale.ROOT))
                        description.set(Configuration.description)

                        url.set(Configuration.githubUrl)

                        licenses {
                            license {
                                name.set("The Apache License, Version 2.0")
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }

                        developers {
                            developer {
                                id.set(Configuration.developerId)
                                name.set(Configuration.developerName)
                                email.set(Configuration.developerEmail)
                            }
                        }

                        scm {
                            connection.set(Configuration.gitUrl)
                            developerConnection.set(Configuration.gitUrl)
                            url.set(Configuration.githubUrl)
                        }

                        issueManagement {
                            system.set("GitHub")
                            url.set(Configuration.issueUrl)
                        }
                    }
                }
            }

            repositories {
                // For testing purpose
                localRepository(project, "repo")

                mavenCentralRepository(project)
                sonatypeSnapshotRepository(project)
            }
        }

        with(signing) {
            useInMemoryPgpKeys(
                propertyOrEnvOrNull("OSSRH_SIGNING_KEY_ID"),
                propertyOrEnvOrNull("OSSRH_SIGNING_KEY"),
                propertyOrEnvOrNull("OSSRH_SIGNING_PASSWORD")
            )
            sign(publishing.publications["release"])
        }
    }
}