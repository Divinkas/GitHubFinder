package com.divinkas.app.githubfinder.base.ui

abstract class BaseViewModelScreen<VM : BaseViewModel>(layoutRes: Int) : BaseScreen(layoutRes) {
    protected abstract val viewModel: VM
}
