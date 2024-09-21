plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.kamacite"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(11)
}

tasks.test {

    useJUnitPlatform()

    testLogging {

        events("passed", "skipped", "failed", "standard_out")

        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
