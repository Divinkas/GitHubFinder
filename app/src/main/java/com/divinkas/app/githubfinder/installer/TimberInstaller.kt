package com.divinkas.app.githubfinder.installer

import android.app.Application
import com.divinkas.app.githubmodule.installer.ApplicationInstaller
import timber.log.Timber

object TimberInstaller : ApplicationInstaller {
    override fun install(application: Application) {
        Timber.plant(Timber.DebugTree())
    }
}
