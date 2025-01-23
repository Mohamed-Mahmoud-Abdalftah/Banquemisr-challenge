plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id("kotlin-kapt")


}

android {
    namespace = "banquemisr.challenge05.home"
    compileSdk = 35

    defaultConfig {
        minSdk = 24


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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))


    //region Data Dependencies
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.hilt.core)
    implementation(libs.retrofit.core)
    implementation(libs.ui.tooling.preview.android)
    kapt(libs.hilt.compiler)
    implementation(libs.retrofit.gson.converter)
    //endregion

    //region Presentation Dependencies
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.hilt.navigation)
    implementation(libs.compose.ui.graphics)
    implementation(libs.pager)
    implementation(libs.compose.ui.material)
    implementation(libs.compose.activity)
    implementation(libs.coil)
    implementation(libs.runtime)
    implementation (libs.paging.runtime.ktx)
    implementation (libs.paging.compose)
    implementation (libs.androidx.constraintlayout.compose)

    //endregion
}