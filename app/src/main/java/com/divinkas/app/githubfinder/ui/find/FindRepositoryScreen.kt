package com.divinkas.app.githubfinder.ui.find

import com.divinkas.app.githubfinder.R
import com.divinkas.app.githubfinder.base.ui.BaseViewModelScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindRepositoryScreen : BaseViewModelScreen<FindViewModel>(R.layout.main_screen) {
    override val viewModel: FindViewModel by viewModel()

    override fun setupUi() {
    }
}