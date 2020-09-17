package com.divinkas.app.githubmodule.components

import com.divinkas.app.githubmodule.arch.localdata.LocalStorage
import com.divinkas.app.githubmodule.bean.api.Repository

class RepositoryLocalComponentImpl(
    private val localStorage: LocalStorage
) : RepositoryLocalComponent {

    override fun loadSavedRepository() = localStorage.loadSavedRepository()

    override fun addToSavedRepository(repository: Repository) {
        localStorage.addToSavedRepository(repository)
    }

    override fun removeSavedRepository(idRepository: Int) {
        localStorage.removeSavedRepository(idRepository)
    }
}