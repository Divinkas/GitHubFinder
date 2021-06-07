package com.divinkas.app.githubfinder.configuration

import com.divinkas.app.githubmodule.configuration.ConfigUtils

object Configuration {
    private const val LINK_API = "https://api.github.com/"
    const val LIMIT_ON_PAGE = 30
    private const val LIMIT_RESULTS_COUNT = 15
    const val STEP_PAGINATION = 2
    const val STEP_PAGINATION_NEXT = 3

    fun getConfigUtils() = ConfigUtils(LINK_API, LIMIT_RESULTS_COUNT)
}