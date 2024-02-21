plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = ("com.example.cats")
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cats"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled =  false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":presentation:cats"))
    implementation(project(":presentation:home"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(libs.androidx.core.fragment.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.dagger.runtime)
    ksp(libs.dagger.compiler)

    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    ksp (libs.androidx.room.compiler)

    implementation (libs.ktor.client.content.negotiation)
    implementation (libs.ktor.client.android)
}