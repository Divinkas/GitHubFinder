package com.divinkas.app.githubmodule

import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.api.Repository
import com.divinkas.app.githubmodule.bean.ServerResult
import com.divinkas.app.githubmodule.configuration.ConfigUtils
import com.divinkas.app.githubmodule.installer.KoinInstaller
import org.koin.core.KoinComponent
import org.koin.core.get

class GitHubModuleAPI(
    private val configUtils: ConfigUtils
): KoinComponent {
    private var gitHubModule: GitHubModule

    companion object {
        var instance: GitHubModuleAPI? = null
            private set
    }

    init {
        KoinInstaller(configUtils).install()
        gitHubModule = get()
        instance = this
    }

    fun findRepositoriesByName(name: String, page: Int): ServerResult<FindResult> {
        return gitHubModule.findRepositoriesByName(name, page)
    }

    fun saveRepository(repository: Repository) {
        gitHubModule.saveRepository(repository)
    }

    fun loadSavedRepository(): List<Repository>? {
        return gitHubModule.loadSavedRepository()
    }

    fun removeSavedRepository(idRepository: Int) {
        gitHubModule.removeSavedRepository(idRepository)
    }
}