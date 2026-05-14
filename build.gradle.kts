plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.16.0"
}

group = providers.gradleProperty("pluginGroup").get()
version = providers.gradleProperty("pluginVersion").get()

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaUltimate(providers.gradleProperty("platformVersion"))
    }
}

java {
    val jv = JavaVersion.toVersion(providers.gradleProperty("javaVersion").get())
    sourceCompatibility = jv
    targetCompatibility = jv
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(providers.gradleProperty("javaVersion").get()))
    }
}

// Map the existing non-standard project layout to Gradle source sets.
sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources {
            setSrcDirs(listOf("resources"))
            // Include META-INF/plugin.xml and other META-INF resources in the produced JAR.
            srcDir(".")
            include("META-INF/**", "icons/**")
        }
    }
}

intellijPlatform {
    pluginConfiguration {
        name = providers.gradleProperty("pluginName")
        version = providers.gradleProperty("pluginVersion")
        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
            untilBuild = provider { null }
        }
    }

    publishing {
        token = providers.environmentVariable("JETBRAINS_MARKETPLACE_TOKEN")
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

tasks {
    wrapper {
        gradleVersion = "9.5.0"
        distributionType = Wrapper.DistributionType.BIN
    }

}
