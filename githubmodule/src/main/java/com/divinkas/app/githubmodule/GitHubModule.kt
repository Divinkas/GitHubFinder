package com.divinkas.app.githubmodule

import com.divinkas.app.githubmodule.bean.ServerError
import com.divinkas.app.githubmodule.bean.ServerResult
import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.api.Repository
import com.divinkas.app.githubmodule.components.RepositoryLocalComponent
import com.divinkas.app.githubmodule.components.RepositoryWebComponent
import kotlinx.coroutines.*
import timber.log.Timber

internal class GitHubModule(
    private val repositoryWebComponent: RepositoryWebComponent,
    private val repositoryLocalComponent: RepositoryLocalComponent
) {
    fun findRepositoriesByName(name: String, page: Int): ServerResult<FindResult> {
        var apiResult: ServerResult<FindResult>? = null

        runBlocking {
            val searchResult = getSearchResult(name, page)
            Timber.i("api: find repository result = $searchResult")

            apiResult = generateSingleApiResult(searchResult)
            if (apiResult == null) {
                apiResult = ServerResult.Error(ServerError.Unknown())
            }
        }

        return apiResult!!
    }

    fun saveRepository(repository: Repository) {
        repositoryLocalComponent.addToSavedRepository(repository)
    }

    fun loadSavedRepository(): List<Repository> {
        return repositoryLocalComponent.loadSavedRepository()
    }

    fun removeSavedRepository(idRepository: Int) {
        repositoryLocalComponent.removeSavedRepository(idRepository)
    }

    private suspend fun findRepositoriesAsync(name: String, page: Int) = GlobalScope.async {
        Timber.i("api: start finding $name / $page")
        repositoryWebComponent.findRepositoriesByName(name, page)
    }

    private suspend fun getSearchResult(name: String, page: Int) = coroutineScope {
        val results: MutableList<Deferred<ServerResult<FindResult>>> = ArrayList()

        results.add(findRepositoriesAsync(name, page))
        results.add(findRepositoriesAsync(name, page + 1))

        results.awaitAll()
    }

    private suspend fun generateSingleApiResult(results: List<ServerResult<FindResult>>) =
        withContext(Dispatchers.IO) {
            var apiServerResult: ServerResult<FindResult>? = null
            results.forEach {
                if (it is ServerResult.Success) {
                    if (apiServerResult == null) {
                        apiServerResult = it
                    } else {
                        if (!it.value.items.isNullOrEmpty()) {
                            (apiServerResult as ServerResult.Success).value.items?.addAll(it.value.items)
                        }
                    }
                }
            }
            apiServerResult
        }
}