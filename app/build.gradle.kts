plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.chililabstest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chililabstest"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GIPHY_API_KEY", "\"ahXehDVV501RGwyo6Uqyh104uGc6d0SS\"")
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
        buildConfig = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinDependencies.coroutines)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.material3)

    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.core.ktx)
    testImplementation(libs.testng)
    ksp(libs.koin.compiler)

    implementation(libs.retrofitDependencies.retrofit)
    implementation(libs.retrofitDependencies.converter.gson)
    implementation(libs.gsonDependencies.gson)

    implementation(libs.pagingDependencies.runtime)
    implementation(libs.pagingDependencies.compose)

    androidTestImplementation(libs.androidx.junit)
    testRuntimeOnly(libs.junit.platform)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.mockitoDependency.core)
    testImplementation(libs.mockitoDependency.kotlin)
    testImplementation(libs.kotlinDependencies.coroutines.test)
    testImplementation(libs.mockitoDependency.inline)
}