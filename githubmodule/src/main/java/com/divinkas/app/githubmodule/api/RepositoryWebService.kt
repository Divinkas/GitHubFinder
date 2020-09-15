package com.divinkas.app.githubmodule.api

import com.divinkas.app.githubmodule.bean.Repository
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryWebService {
    @GET("/users")
    fun findRepositoriesByName(@Query("page") page:Int): List<Repository>
}