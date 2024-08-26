plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.pavelshelkovenko.effmobcontest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pavelshelkovenko.effmobcontest"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.bundles.core)
    implementation(libs.bundles.ui.common)
    implementation(libs.bundles.navigation)
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":feature-profile"))
    implementation(project(":feature-messages"))
    implementation(project(":feature-responses"))
    implementation(project(":feature-favorite-vacancies"))
    implementation(project(":feature-search-vacancies"))
    implementation(project(":feature-login"))
    implementation(project(":feature-vacancy-details"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}