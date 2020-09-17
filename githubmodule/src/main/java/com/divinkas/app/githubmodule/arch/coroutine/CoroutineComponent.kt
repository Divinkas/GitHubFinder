package com.divinkas.app.githubmodule.arch.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface CoroutineComponent {
    fun launchOnMain(block: suspend CoroutineScope.() -> Unit): Job
    fun launchOnIo(block: suspend CoroutineScope.() -> Unit): Job
    fun launch(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit): Job
}