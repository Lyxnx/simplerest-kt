@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("common") {
            from(files("android-gradle-catalogs/versions-common/libs.versions.toml"))
        }
    }
}

rootProject.name = "simplerest-kt"