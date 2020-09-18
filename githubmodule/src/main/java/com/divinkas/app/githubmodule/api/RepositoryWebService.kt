package com.divinkas.app.githubmodule.api

import com.divinkas.app.githubmodule.bean.api.FindResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryWebService {
    @GET("search/repositories")
    fun findRepositoriesByName(
        @Query("q") repositoryName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): FindResult
}