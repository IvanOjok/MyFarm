// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
        maven("https://plugins.gradle.org/m2/")
        maven("maven.google.com")
        maven("https://jitpack.io")
    }

    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.9.1")
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}