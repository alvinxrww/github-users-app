plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.githubusers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.githubusers"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "API_TOKEN", "\"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\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "API_TOKEN", "\"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\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.fragment.ktx)
}