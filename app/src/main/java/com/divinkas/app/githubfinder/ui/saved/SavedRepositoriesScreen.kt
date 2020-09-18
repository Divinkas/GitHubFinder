package com.divinkas.app.githubfinder.ui.saved

import androidx.recyclerview.widget.LinearLayoutManager
import com.divinkas.app.githubfinder.R
import com.divinkas.app.githubfinder.base.ui.BaseViewModelScreen
import com.divinkas.app.githubfinder.ui.adapter.RepositoryListAdapter
import com.divinkas.app.githubfinder.utils.observeLiveData
import com.divinkas.app.githubfinder.utils.toast
import kotlinx.android.synthetic.main.saved_screen.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedRepositoriesScreen: BaseViewModelScreen<SavedRepositoryViewModel>(R.layout.saved_screen) {
    override val viewModel: SavedRepositoryViewModel by viewModel()
    private val adapter = RepositoryListAdapter()

    override fun setupUi() {
        initRecycler()
        addListeners()
        viewModel.loadSavedRepository()
    }

    override fun setupLiveDataObservers() {
        observeLiveData(viewModel.localRepositoryLiveData) {
            if (!it.isNullOrEmpty()) {
                adapter.addAll(it)
            }
            swipe_repository.isRefreshing = false
        }
    }

    private fun initRecycler() {
        adapter.isLocalRepository = true
        rv_repositories.layoutManager = LinearLayoutManager(context)
        rv_repositories.adapter = adapter
    }

    private fun addListeners() {
        swipe_repository.setOnRefreshListener {
            adapter.clean()
            viewModel.loadSavedRepository()
        }

        adapter.removeAction = {
            viewModel.removeFromSaved(it.id)
            adapter.remove(it)
            toast(R.string.repository_was_removed)
        }
    }
}