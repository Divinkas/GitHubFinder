package com.divinkas.app.githubmodule.components

import com.divinkas.app.githubmodule.api.RepositoryWebService
import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.ServerResult
import com.divinkas.app.githubmodule.arch.network.HttpCommunicationComponent
import com.divinkas.app.githubmodule.configuration.ConfigUtils

class RepositoryWebComponentImpl(
    private val httpCommunicationComponent: HttpCommunicationComponent,
    private val configUtils: ConfigUtils
) : RepositoryWebComponent {

    override suspend fun findRepositoriesByName(
        name: String,
        page: Int
    ): ServerResult<FindResult> {
        val webService = httpCommunicationComponent.createService(RepositoryWebService::class.java)
        return httpCommunicationComponent.execute<FindResult> {
            webService.findRepositoriesByName(
                repositoryName = name,
                perPage = configUtils.limitOnPage,
                page = page
            )
        }
    }
}