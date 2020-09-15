plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Configuration.compileSdkVersion)
    buildToolsVersion(Configuration.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Configuration.minSdkVersion)
        targetSdkVersion(Configuration.targetSdkVersion)

        applicationId = "com.divinkas.app.githubfinder"
        versionCode = 21
        versionName = "1.0.0-RC01"
        multiDexEnabled = true
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Dependencies.kotlinStandardLibrary)
    implementation(project(":githubmodule"))

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreExtensions)
    implementation(Dependencies.AndroidX.multiDex)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
    implementation(Dependencies.AndroidX.viewModel)
    implementation(Dependencies.AndroidX.encryptedSharedPreferences)

    implementation(Dependencies.Misc.timber)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.coreExt)
    implementation(Dependencies.Koin.scope)
    implementation(Dependencies.Koin.viewModel)
    implementation(Dependencies.Koin.ext)

    implementation(Dependencies.Others.glide)
    implementation(Dependencies.Others.circleimageview)

    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.Koin.test)
}
