plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.converter.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.client.okhttp)
    implementation(libs.client.logging.interceptor)
}