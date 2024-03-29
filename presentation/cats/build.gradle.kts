plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt.android.plugin)
}

android {
    namespace = "com.example.main"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":presentation:uikit"))
    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.fragment.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.hilt.navigation.compose)

    implementation(libs.glide.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.glide.compose)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.android.material)

    testImplementation(libs.junit)
    testImplementation (libs.kotlin.test)
    testImplementation (libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test.manifest)
}