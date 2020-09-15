package com.divinkas.app.githubfinder.configuration

import com.divinkas.app.githubmodule.configuration.ConfigUtils

object Configuration {
    private const val LINK_API = "hasdasdasdasdasd"

    fun getConfigUtils() = ConfigUtils(LINK_API)
}