package com.divinkas.app.githubfinder.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.divinkas.app.githubfinder.base.livedata.LoadingViewState
import com.divinkas.app.githubfinder.base.livedata.LoadingViewStateLiveData
import com.divinkas.app.githubfinder.base.livedata.ViewState
import com.divinkas.app.githubfinder.base.livedata.ViewStateLiveData
import timber.log.Timber

fun <T> Fragment.observeViewState(liveData: ViewStateLiveData<T>, block: (data: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        if (it is ViewState.Data) {
            Timber.tag(javaClass.simpleName)
            Timber.d("received event $it")
            it.data.apply(block)
        }
    })
}

fun <T> Fragment.observeViewState(liveData: LoadingViewStateLiveData<T>, block: (state: LoadingViewState<T>) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        if (it !is LoadingViewState.NoneObservable) {
            Timber.tag(javaClass.simpleName)
            Timber.d("received event $it")
            it.apply(block)
        }
    })
}

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (data: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        Timber.tag(javaClass.simpleName)
        Timber.d("received event: $it")
        block(it)
    })
}

fun Fragment.addBackPressedCallback(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    })
}
