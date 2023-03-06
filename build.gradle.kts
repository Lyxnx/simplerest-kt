import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.10"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

group = "net.lyxnx"
version = "2.1.3"

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.gson)
    implementation(libs.kotlinx.coroutines)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
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
        register<MavenPublication>("gpr") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/Lyxnx/simplerest-kt")
            credentials {
                username = project.property("gpr.username") as? String ?: System.getenv("USERNAME")
                password = project.property("gpr.token") as? String ?: System.getenv("TOKEN")
            }
        }
    }
}