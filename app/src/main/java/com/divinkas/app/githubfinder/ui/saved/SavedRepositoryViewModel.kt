package com.divinkas.app.githubfinder.ui.saved

import androidx.lifecycle.viewModelScope
import com.divinkas.app.githubfinder.ui.GitHubViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SavedRepositoryViewModel : GitHubViewModel() {
    //live data variables

    fun loadSavedRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            gitHubModuleAPI.loadSavedRepository()
        }
    }

    fun removeFromSaved() {
        viewModelScope.async {
            gitHubModuleAPI.removeSavedRepository()
        }
    }
}