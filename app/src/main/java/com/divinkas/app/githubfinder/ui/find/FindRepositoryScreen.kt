package com.divinkas.app.githubfinder.ui.find

import com.divinkas.app.githubfinder.R
import com.divinkas.app.githubfinder.base.ui.BaseScreen
import org.koin.android.ext.android.inject

class FindRepositoryScreen : BaseScreen(R.layout.main_screen) {
    private val viewModel: FindViewModel by inject()

    override fun setupUi() {
    }
}