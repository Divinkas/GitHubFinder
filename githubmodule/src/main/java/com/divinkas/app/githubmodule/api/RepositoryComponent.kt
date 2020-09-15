package com.divinkas.app.githubmodule.api

import com.divinkas.app.githubmodule.bean.Repository
import com.divinkas.app.githubmodule.bean.ServerResult

interface RepositoryComponent {
    suspend fun findRepositoriesByName(name: String, page: Int, limitOnPage: Int): ServerResult<List<Repository>>
    suspend fun addToSavedRepository(repository: Repository)
    suspend fun loadSavedRepository()
    suspend fun removeSavedRepository(repository: Repository)
}
