plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kamacite"

include("reference")
include("tools")

// jvm translation
include("jvm")
project(":jvm").projectDir = file("translations/jvm")
