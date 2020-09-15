package com.divinkas.app.githubfinder.base.livedata

import androidx.lifecycle.LiveData

open class ViewStateLiveData<T> : LiveData<ViewState<T>>() {
    protected open fun setState(state: ViewState<T>) {
        value = state
    }

    protected open fun postState(state: ViewState<T>) {
        postValue(state)
    }
}
