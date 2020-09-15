package com.divinkas.app.githubfinder.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class OnScrollHelper(
    private var isLastPage: Boolean,
    private val callback: OnScrollCallback
) : RecyclerView.OnScrollListener() {
    private var isLoadingMoreSeries: Boolean = false
    private var CURRENT_PAGE: Int = 1

    fun loadMore(loadMore: Boolean) {
        isLoadingMoreSeries = loadMore
    }

    fun setLastPage(lastPage: Boolean) {
        isLastPage = lastPage
    }

    fun setCURRENT_PAGE(CURRENT_PAGE: Int) {
        this.CURRENT_PAGE = CURRENT_PAGE
    }

    fun getCURRENT_PAGE(): Int {
        return CURRENT_PAGE
    }

    fun isLoadingMoreSeries(): Boolean {
        return isLoadingMoreSeries
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager: LinearLayoutManager = Objects
            .requireNonNull<RecyclerView.LayoutManager>(recyclerView.layoutManager) as LinearLayoutManager

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItems = layoutManager.findFirstVisibleItemPosition()

        if (!isLoadingMoreSeries) {
            if (visibleItemCount + firstVisibleItems >= totalItemCount && !isLastPage) {
                isLoadingMoreSeries = true
                CURRENT_PAGE += 1
                callback.onScrolledToEnd()
            }
        }
    }

    interface OnScrollCallback {
        fun onScrolledToEnd()
    }
}
