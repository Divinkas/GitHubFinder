package com.divinkas.app.githubfinder.ui

import com.divinkas.app.githubfinder.base.ui.BaseViewModel
import com.divinkas.app.githubmodule.GitHubModuleAPI
import org.koin.core.inject

open class GitHubViewModel: BaseViewModel() {
    protected val gitHubModuleAPI: GitHubModuleAPI by inject()
}