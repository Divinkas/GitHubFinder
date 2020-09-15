package com.divinkas.app.githubmodule

import com.divinkas.app.githubmodule.configuration.ConfigUtils
import com.divinkas.app.githubmodule.installer.KoinInstaller
import org.koin.core.KoinComponent
import org.koin.core.get

class GitHubModuleAPI(
    private val configUtils: ConfigUtils
): KoinComponent {
    private var gitHubModule: GitHubModule? = null

    companion object {
        var instance: GitHubModuleAPI? = null
            private set
    }

    init {
        KoinInstaller.install(configUtils)
        gitHubModule = get()
        instance = this
    }

    fun findRepositoriesByName(name: String, page: Int, limitOnPage: Int) {
        // async in two threads
    }

    fun saveRepository() {
    }

    fun loadSavedRepository() {
    }

    fun removeSavedRepository() {
    }
}