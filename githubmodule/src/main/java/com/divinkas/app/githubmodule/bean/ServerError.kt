package com.divinkas.app.githubmodule.bean

sealed class ServerError {
    class Unknown(val message: String = "") : ServerError()
    class Backend(val message: String = "") : ServerError()
}