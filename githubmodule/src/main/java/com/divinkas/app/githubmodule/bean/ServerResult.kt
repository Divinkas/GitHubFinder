package com.divinkas.app.githubmodule.bean

sealed class ServerResult<out T> {
    data class Error(val error: ServerError) : ServerResult<Nothing>()
    data class Success<T>(val value: T) : ServerResult<T>()
}
