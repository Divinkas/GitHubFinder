package com.divinkas.app.githubmodule.bean.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FindResult(
    @SerializedName("total_count")
    val totalCount: Int = 0,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,

    @SerializedName("items")
    val items: MutableList<Repository>?
): Serializable