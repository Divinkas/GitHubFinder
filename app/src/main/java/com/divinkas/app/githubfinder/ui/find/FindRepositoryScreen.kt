package com.divinkas.app.githubfinder.ui.find

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.divinkas.app.githubfinder.R
import com.divinkas.app.githubfinder.base.ui.BaseViewModelScreen
import com.divinkas.app.githubfinder.ui.adapter.RepositoryListAdapter
import com.divinkas.app.githubfinder.utils.*
import com.divinkas.app.githubmodule.bean.ServerResult
import kotlinx.android.synthetic.main.main_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindRepositoryScreen : BaseViewModelScreen<FindViewModel>(R.layout.main_screen) {
    override val viewModel: FindViewModel by viewModel()
    private val adapter = RepositoryListAdapter()
    private lateinit var scrolledHelper: ScrolledHelper

    override fun setupUi() {
        inflateToolbarMenu()
        initRecycler()
        addListeners()
    }

    override fun setupLiveDataObservers() {
        observeLiveData(viewModel.repositoryLiveData) {
            when (it) {
                is ServerResult.Success -> {
                    if (!it.value.items.isNullOrEmpty()) {
                        adapter.addAll(it.value.items!!)
                        scrolledHelper.loadMore(false)
                    } else {
                        toast(R.string.no_results)
                    }
                    swipe_repository.isRefreshing = false
                }
                else -> toast(R.string.something_went_wrong)
            }
        }

        observeLiveData(viewModel.isLastPageRepositoryLiveData) {
            scrolledHelper.setLastPage(it)
        }
    }

    private fun inflateToolbarMenu() {
        search_toolbar.title = getString(R.string.app_name)
        search_toolbar.inflateMenu(R.menu.search_menu)
        val searchItem = search_toolbar.menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.enter_repository)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String?): Boolean {
                val isInvalidText = name.isNullOrEmpty()
                if (!isInvalidText) {
                    runWithNetworkConnection { viewModel.findRepositoryByName(name!!) }
                } else {
                    toast(R.string.field_is_empty)
                }
                return !isInvalidText
            }

            override fun onQueryTextChange(newText: String?) = true
        })

        search_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.goToSaved -> navigateTo(FindRepositoryScreenDirections.toSavedFragment())
            }
            true
        }
    }

    private fun initRecycler() {
        scrolledHelper = ScrolledHelper(object : ScrolledHelper.OnScrollCallback {
            override fun onScrolledToEnd() {
                runWithNetworkConnection { viewModel.loadNextPage() }
            }
        })

        rv_repositories.layoutManager = LinearLayoutManager(context)
        rv_repositories.addOnScrollListener(scrolledHelper)
        rv_repositories.adapter = adapter
    }

    private fun addListeners() {
        swipe_repository.setOnRefreshListener {
            adapter.clean()
            viewModel.clearAndFindAgain()
        }

        adapter.saveAction = {
            viewModel.addToSavedRepository(it)
            toast(R.string.repository_was_saved)
        }
    }
}