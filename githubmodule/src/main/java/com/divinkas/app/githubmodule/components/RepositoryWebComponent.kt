package com.divinkas.app.githubmodule.components

import com.divinkas.app.githubmodule.bean.api.FindResult
import com.divinkas.app.githubmodule.bean.ServerResult

interface RepositoryWebComponent {
    suspend fun findRepositoriesByName(name: String, page: Int): ServerResult<FindResult>
}
