object Dependencies {
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val berTlv = "com.payneteasy:ber-tlv:${Versions.berTlv}"

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.core}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.android}"
    }

    object ExoPlayer {
        const val core = "com.google.android.exoplayer:exoplayer-core:${Versions.exoPlayer}"
        const val ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoPlayer}"
    }

    object Misc {
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.Misc.leakCanary}"
        const val dexter = "com.karumi:dexter:${Versions.Misc.dexter}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Misc.timber}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object View {
        const val pinView = "com.chaos.view:pinview:${Versions.View.pinView}"
        const val maskedEditText = "ru.egslava:MaskedEditText:${Versions.View.maskedEditText}"
        const val stepProgressBar = "kr.co.prnd:stepprogressbar:${Versions.View.stepProgressBar}"
        const val lottie = "com.airbnb.android:lottie:${Versions.View.lottie}"
    }

    object Koin {
        const val android = "org.koin:koin-android:${Versions.koin}"
        const val core = "org.koin:koin-core:${Versions.koin}"
        const val coreExt = "org.koin:koin-core-ext:${Versions.koin}"
        const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
        const val test = "org.koin:koin-test:${Versions.koin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.androidx}"
        const val coreExtensions = "androidx.core:core-ktx:${Versions.AndroidX.androidx}"
        const val multiDex = "androidx.multidex:multidex:${Versions.AndroidX.multiDex}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val encryptedSharedPreferences = "androidx.security:security-crypto:${Versions.AndroidX.encryptedSharedPreferences}"
    }

    object Others {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val circleimageview = "de.hdodenhof:circleimageview:${Versions.Other.circleImageView}"
    }
}
