package com.divinkas.app.githubfinder.ui.saved

import com.divinkas.app.githubfinder.ui.GitHubViewModel
import timber.log.Timber

class SavedRepositoryViewModel : GitHubViewModel() {
    fun onCreate() {
        Timber.i("onCreate")
    }
}