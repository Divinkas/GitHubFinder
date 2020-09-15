package com.divinkas.app.githubfinder.base.livedata

class MutableLoadingViewStateLiveData<T> : LoadingViewStateLiveData<T>() {
    public override fun setState(state: LoadingViewState<T>) {
        super.setState(state)
    }

    public override fun postState(state: LoadingViewState<T>) {
        super.postState(state)
    }
}
