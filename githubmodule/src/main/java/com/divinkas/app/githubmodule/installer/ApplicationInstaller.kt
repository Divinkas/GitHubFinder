package com.divinkas.app.githubmodule.installer

import android.app.Application

interface ApplicationInstaller {
    fun install(application: Application)
}