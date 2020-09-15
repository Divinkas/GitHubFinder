package com.divinkas.app.githubmodule.components.localdata

interface LocalDataComponent {
    fun getSavedRepository()
    fun saveRepository()
    fun removeSavedRepository()
}