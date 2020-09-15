package com.divinkas.app.githubmodule.bean

sealed class BackendError : RuntimeException() {
    data class Logical(private val code: String = "", private val data: String = "") : BackendError()
    data class NotFound(private val details: String = "") : BackendError()
    data class System(private val error: String = "", private val details: String = "") : BackendError()
    data class Unknown(private val details: String = "") : BackendError()
}
