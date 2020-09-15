package com.divinkas.app.githubfinder.ui.find

import androidx.lifecycle.viewModelScope
import com.divinkas.app.githubfinder.base.livedata.LoadingViewState
import com.divinkas.app.githubfinder.base.livedata.LoadingViewStateLiveData
import com.divinkas.app.githubfinder.base.livedata.MutableLoadingViewStateLiveData
import com.divinkas.app.githubfinder.ui.GitHubViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FindViewModel : GitHubViewModel() {
    companion object {
        private const val LIMIT_ON_PAGE = 30
    }

    private val _loadLiveData: MutableLoadingViewStateLiveData<Int> = MutableLoadingViewStateLiveData()
    val repositoriesLiveData: LoadingViewStateLiveData<Int> = _loadLiveData

    fun findRepositoryByName(name: String, page: Int) {
        viewModelScope.launch {
            _loadLiveData.setState(LoadingViewState.Loading())
            //parse API
            gitHubModuleAPI.findRepositoriesByName(name, page, LIMIT_ON_PAGE)
        }
    }

    fun addToSavedRepository() {
        viewModelScope.async {
            gitHubModuleAPI.saveRepository()
        }
    }
}