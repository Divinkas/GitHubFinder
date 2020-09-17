package com.divinkas.app.githubfinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.divinkas.app.githubfinder.base.ui.BaseViewModel
import com.divinkas.app.githubmodule.GitHubModuleAPI
import com.divinkas.app.githubmodule.bean.api.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.inject

open class GitHubViewModel: BaseViewModel() {
    protected val gitHubModuleAPI: GitHubModuleAPI by inject()

    protected val _localRepositoriesLiveData: MutableLiveData<List<Repository>> = MutableLiveData()
    val localRepositoryLiveData: LiveData<List<Repository>> = _localRepositoriesLiveData

    protected val _isLastPageLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLastPageRepositoryLiveData: LiveData<Boolean> = _isLastPageLiveData

    fun loadSavedRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            val searchedRepositories = gitHubModuleAPI.loadSavedRepository()
            if (searchedRepositories != null) {
                _localRepositoriesLiveData.value = searchedRepositories
            }
        }
    }

    fun removeFromSaved(idRepository: Int) {
        viewModelScope.async {
            gitHubModuleAPI.removeSavedRepository(idRepository)
        }
    }

    fun addToSavedRepository(repository: Repository) {
        viewModelScope.async {
            gitHubModuleAPI.saveRepository(repository)
        }
    }
}