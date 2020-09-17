package com.divinkas.app.githubmodule.components

import com.divinkas.app.githubmodule.bean.api.Repository

interface RepositoryLocalComponent {
    fun addToSavedRepository(repository: Repository)
    fun loadSavedRepository(): List<Repository>
    fun removeSavedRepository(idRepository: Int)
}