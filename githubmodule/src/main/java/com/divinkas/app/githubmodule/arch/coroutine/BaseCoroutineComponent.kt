package com.divinkas.app.githubmodule.arch.coroutine

import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class BaseCoroutineComponent: CoroutineComponent {
    private val loggingExceptionHandler = CoroutineExceptionHandler { _, t -> Timber.e(t) }

    override fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(context = Dispatchers.Main + loggingExceptionHandler, block = block)
    }

    override fun launchOnIo(block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(context = Dispatchers.IO + loggingExceptionHandler, block = block)
    }

    override fun launch(context: CoroutineContext, block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(context = context + loggingExceptionHandler, block = block)
    }
}