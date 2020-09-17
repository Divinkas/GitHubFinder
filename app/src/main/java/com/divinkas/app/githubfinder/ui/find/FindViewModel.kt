package com.divinkas.app.githubfinder.ui.find

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.divinkas.app.githubfinder.configuration.Configuration
import com.divinkas.app.githubfinder.ui.GitHubViewModel
import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.ServerResult
import kotlinx.coroutines.launch

class FindViewModel : GitHubViewModel() {
    private var page: Int = 1
    private var repositorySearchName: String? = null

    protected val _repositoriesLiveData: MutableLiveData<ServerResult<FindResult>> =
        MutableLiveData()
    val repositoryLiveData: LiveData<ServerResult<FindResult>> = _repositoriesLiveData

    fun findRepositoryByName(name: String) {
        repositorySearchName = name
        loadNextPage()
    }

    fun loadNextPage() {
        if (!repositorySearchName.isNullOrEmpty()) {
            viewModelScope.launch {
                when (val repoResult =
                    gitHubModuleAPI.findRepositoriesByName(repositorySearchName!!, page)) {
                    is ServerResult.Success -> {
                        _repositoriesLiveData.value = repoResult
                        val items = repoResult.value.items
                        _isLastPageLiveData.value =
                            items.isNullOrEmpty() || items.count() != Configuration.LIMIT_ON_PAGE
                    }
                }

                page += Configuration.STEP_PAGINATION
            }
        }
    }

    fun clearAndFindAgain() {
        page = 1
        loadNextPage()
    }
}