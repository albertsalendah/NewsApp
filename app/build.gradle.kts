plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.richard_salendah.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.richard_salendah.newsapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation (libs.androidx.core.ktx.v180)
    implementation (platform(libs.kotlin.bom))
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.activity.compose)
    implementation (platform(libs.androidx.compose.bom))
    implementation (libs.androidx.ui)
    implementation (libs.androidx.ui.graphics)
    implementation (libs.androidx.ui.tooling.preview)
    implementation (libs.androidx.material3)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.paging.compose.android)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v115)
    androidTestImplementation (libs.androidx.espresso.core.v351)
    androidTestImplementation (platform(libs.androidx.compose.bom))
    androidTestImplementation (libs.androidx.ui.test.junit4)
    debugImplementation (libs.androidx.ui.tooling)
    debugImplementation (libs.androidx.ui.test.manifest)
    implementation (libs.androidx.core.splashscreen)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    implementation (libs.kotlinx.serialization.json)
    implementation (libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.coil.compose)
    implementation (libs.androidx.datastore.preferences)
    implementation (libs.androidx.foundation)
    implementation (libs.accompanist.systemuicontroller)
    implementation(libs.retrofit2.converter.gson)
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation (libs.accompanist.swiperefresh)

    implementation (libs.accompanist.swiperefresh)
}
