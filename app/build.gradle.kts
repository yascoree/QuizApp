plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
}


android {
    namespace = "com.quizapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.quizapp"
        minSdk = 29
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
        buildConfig = true
    }
}

configurations.all {
    resolutionStrategy {
        force("androidx.browser:browser:1.8.0")
    }
}

dependencies {
    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))
    // Add the dependency for the Firebase SDK for Google Analytics
    implementation(libs.firebase.analytics)
    // Add the dependency for the Firebase Authentication library
    implementation(libs.firebase.auth)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // Supabase dependencies
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.storage)
    implementation(libs.supabase.postgrest)
    implementation(libs.ktor.client.android)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Image loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
}