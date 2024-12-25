plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mpprojectmtvtracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mpprojectmtvtracker"
        minSdk = 24
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
}

dependencies {

    //implementation(libs.room.common)
    //implementation(libs.room.runtime)
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    //implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor("androidx.room:room-compiler:$room_version")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.2.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")


//    // optional - Kotlin Extensions and Coroutines support for Room
//    implementation("androidx.room:room-ktx:$room_version")
//
//    // optional - RxJava2 support for Room
//    implementation("androidx.room:room-rxjava2:$room_version")
//
//    // optional - RxJava3 support for Room
//    implementation("androidx.room:room-rxjava3:$room_version")
//
//    // optional - Guava support for Room, including Optional and ListenableFuture
//    implementation("androidx.room:room-guava:$room_version")
//
//    // optional - Test helpers
//    testImplementation("androidx.room:room-testing:$room_version")
//
//    // optional - Paging 3 Integration
//    implementation("androidx.room:room-paging:$room_version")
}