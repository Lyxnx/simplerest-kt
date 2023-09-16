@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        val catalogsVersion = "2023.09.16"
        create("common") {
            from("io.github.lyxnx.gradle:versions-common:$catalogsVersion")
        }
        create("external") {
            from("io.github.lyxnx.gradle:versions-external:$catalogsVersion")
        }
    }
}

rootProject.name = "simplerest-kt"