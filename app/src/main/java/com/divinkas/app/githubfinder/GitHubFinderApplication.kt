package com.divinkas.app.githubfinder

import android.app.Application
import com.divinkas.app.githubfinder.configuration.Configuration
import com.divinkas.app.githubfinder.installer.KoinInstaller
import com.divinkas.app.githubfinder.installer.TimberInstaller
import com.divinkas.app.githubmodule.GitHubModuleAPI
import com.divinkas.app.githubmodule.configuration.ConfigUtils

class GitHubFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInstaller.install(this)
        TimberInstaller.install(this)
        GitHubModuleAPI(Configuration.getConfigUtils())
    }
}