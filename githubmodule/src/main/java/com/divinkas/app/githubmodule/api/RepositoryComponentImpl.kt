package com.divinkas.app.githubmodule.api

import com.divinkas.app.githubmodule.bean.Repository
import com.divinkas.app.githubmodule.bean.ServerResult
import com.divinkas.app.githubmodule.components.network.HttpCommunicationComponent

class RepositoryComponentImpl(
    private val httpCommunicationComponent: HttpCommunicationComponent
): RepositoryComponent {
    override suspend fun findRepositoriesByName(name: String, page: Int, limitOnPage: Int): ServerResult<List<Repository>> {
        val webService = httpCommunicationComponent.createService(RepositoryWebService::class.java)
        return httpCommunicationComponent.execute { webService.findRepositoriesByName(page) }
    }

    override suspend fun addToSavedRepository(repository: Repository) {
        TODO("Not yet implemented")
    }

    override suspend fun loadSavedRepository() {
        TODO("Not yet implemented")
    }

    override suspend fun removeSavedRepository(repository: Repository) {
        TODO("Not yet implemented")
    }
}