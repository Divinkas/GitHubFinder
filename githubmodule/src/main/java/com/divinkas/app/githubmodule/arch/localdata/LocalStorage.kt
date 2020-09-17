package com.divinkas.app.githubmodule.arch.localdata

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.divinkas.app.githubmodule.bean.api.Repository
import com.google.gson.Gson

class LocalStorage (
    application: Application
) {
    private val preferences: SharedPreferences = application.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    private val gson: Gson = Gson()

    fun addToSavedRepository(repository: Repository) {
        var savedSet = getLocalRepositoriesSet()

        if (savedSet == null) {
            savedSet = HashSet()
        }

        savedSet.add(gson.toJson(repository))
        updateLocalRepositoriesSet(savedSet)
    }

    fun loadSavedRepository(): List<Repository> {
        val list: MutableList<Repository> = ArrayList()
        getLocalRepositoriesSet()?.forEach {
            list.add(gson.fromJson(it, Repository::class.java))
        }
        return list
    }

    fun removeSavedRepository(idRepository: Int) {
        val savedSet = getLocalRepositoriesSet()
        savedSet?.forEach {
            val repository = gson.fromJson(it, Repository::class.java)
            if (repository.id == idRepository) {
                savedSet.remove(it)
            }
        }
        updateLocalRepositoriesSet(savedSet)
    }

    private fun updateLocalRepositoriesSet(updatedRepositoriesSet: Set<String>?) {
        preferences.edit().putStringSet(LOCAL_REPOSITORY_KEY, updatedRepositoriesSet).apply()
    }

    private fun getLocalRepositoriesSet() = preferences.getStringSet(LOCAL_REPOSITORY_KEY, null)

    companion object {
        private const val PREF_NAME = "repository_preferences"
        private const val LOCAL_REPOSITORY_KEY = "repository_key"
    }
}