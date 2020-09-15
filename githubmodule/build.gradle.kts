plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Configuration.compileSdkVersion)
    buildToolsVersion(Configuration.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Configuration.minSdkVersion)
        targetSdkVersion(Configuration.targetSdkVersion)

        versionCode = 21
    }

    dataBinding.isEnabled = true
}

dependencies {
    implementation(Dependencies.kotlinStandardLibrary)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.coreExt)
    implementation(Dependencies.Koin.scope)
    implementation(Dependencies.Koin.viewModel)
    implementation(Dependencies.Koin.ext)

    implementation(Dependencies.Retrofit.okHttp)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.loggingInterceptor)

    implementation(Dependencies.Misc.timber)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)
}
