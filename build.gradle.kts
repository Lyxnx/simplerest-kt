import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(external.plugins.kradle.kotlin.library)
    alias(common.plugins.kotlin.jvm)
    alias(common.plugins.vanniktech.publish)
}

group = "io.github.lyxnx"
version = "2.2.0"
description = "Simple interface for retrofit"

dependencies {
    api(common.retrofit)
    api(common.retrofit.converter.gson)

    api(common.gson)
    api(common.kotlinx.coroutines.core)
}

mavenPublishing {
    coordinates(group.toString(), name, version.toString())

    publishToMavenCentral(SonatypeHost.Companion.S01, true)
    signAllPublications()

    pom {
        name.set(project.name)
        description.set(project.description)
        inceptionYear.set("2023")
        url.set("https://github.com/Lyxnx/simplerest-kt")
        licenses {
            license {
                name.set("MIT License")
                url.set("http://www.opensource.org/licenses/mit-license.php")
            }
        }
        developers {
            developer {
                id.set("Lyxnx")
                name.set("Lyxnx")
                url.set("https://github.com/Lyxnx")
            }
        }
        scm {
            url.set("https://github.com/Lyxnx/simplerest-kt")
            connection.set("https://github.com/Lyxnx/simplerest-kt.git")
            developerConnection.set("scm:git:ssh://git@github.com/Lyxnx/simplerest-kt.git")
        }
    }
}
