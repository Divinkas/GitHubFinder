package com.divinkas.app.githubmodule

import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.api.Repository
import com.divinkas.app.githubmodule.bean.ServerError
import com.divinkas.app.githubmodule.bean.ServerResult
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

        GlobalScope.launch {
            val results: MutableList<Deferred<ServerResult<FindResult>>> = ArrayList()

            results.add(findRepositoriesAsync(name, page))
            results.add(findRepositoriesAsync(name, page + 1))

            val hasSuccessResult =
                results.awaitAll().findLast { it is ServerResult.Success } != null

            apiResult = if (hasSuccessResult)
                generateSingleApiResult(results)
            else
                ServerResult.Error(ServerError.Unknown())
        }

        Timber.i("api: find repository result = $apiResult")
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

    private suspend fun generateSingleApiResult(results: MutableList<Deferred<ServerResult<FindResult>>>) =
        withContext(Dispatchers.IO) {
            var apiServerResult: ServerResult<FindResult>? = null
            results.awaitAll().forEach {
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