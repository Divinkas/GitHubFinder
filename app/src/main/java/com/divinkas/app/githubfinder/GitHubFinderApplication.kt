package com.divinkas.app.githubfinder

import android.app.Application
import com.divinkas.app.githubfinder.configuration.Configuration
import com.divinkas.app.githubfinder.installer.KoinInstaller
import com.divinkas.app.githubfinder.installer.TimberInstaller
import com.divinkas.app.githubmodule.GitHubModuleAPI

class GitHubFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TimberInstaller.install(application = this)
        KoinInstaller.install(application = this)
        GitHubModuleAPI(configUtils = Configuration.getConfigUtils())
    }
}