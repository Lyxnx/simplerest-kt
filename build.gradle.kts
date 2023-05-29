import com.redmadrobot.build.dsl.githubPackages
import com.redmadrobot.build.dsl.mit
import com.redmadrobot.build.dsl.setGitHubProject

plugins {
    alias(common.plugins.kotlin.jvm)
    alias(common.plugins.redmadrobot.kotlin.library)
    alias(common.plugins.redmadrobot.publish)
}

group = "net.lyxnx"
version = "2.1.4"

dependencies {
    api(common.retrofit)
    api(common.retrofit.converter.gson)

    api(common.gson)
    api(common.kotlinx.coroutines.core)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

redmadrobot {
    publishing {
        pom {
            setGitHubProject("Lyxnx/simplerest-kt")

            licenses {
                mit()
            }
        }
    }
}

publishing {
    repositories {
        githubPackages("Lyxnx/simplerest-kt")
    }
}
