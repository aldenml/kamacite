plugins {
    kotlin("jvm") version "2.0.10"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.github.javaparser:javaparser-core:3.26.2")

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

task("listReference", JavaExec::class) {
    group = "tools"
    description = "List all primitives in the java reference code"
    mainClass = "org.kamacite.tools.ListReferenceKt"
    classpath = sourceSets["main"].runtimeClasspath
}
