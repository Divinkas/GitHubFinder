package com.divinkas.app.githubmodule.arch.network

import com.divinkas.app.githubmodule.bean.ServerResult

interface HttpCommunicationComponent {
    fun getAcceptHeader(): String
    fun <T> createService(clazz: Class<T>): T
    suspend fun <T> execute(request: suspend () -> Any?): ServerResult<T>
}