import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.7.10"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.lyxnx"
version = "2.1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    named<ShadowJar>("shadowJar") {
        archiveFileName.set("${project.name}.jar")

        exclude("net/lyxnx/simplerest/example")

        minimize()
    }

    build {
        dependsOn(shadowJar)
    }

}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class.java) {
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        maven {
            url = uri("https://gitlab.com/api/v4/projects/23209058/packages/maven")
            credentials(HttpHeaderCredentials::class.java) {
                val gitlabKey: String by project
                name = "Private-Token"
                value = gitlabKey
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}