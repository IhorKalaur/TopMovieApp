// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    //id ("com.google.dagger.hilt.android.plugin") version "2.49" apply false
}

buildscript {

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.49")
    }
}