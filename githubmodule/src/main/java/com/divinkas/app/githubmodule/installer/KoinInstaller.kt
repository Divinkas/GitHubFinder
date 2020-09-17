package com.divinkas.app.githubmodule.installer

import com.divinkas.app.githubmodule.GitHubModule
import com.divinkas.app.githubmodule.arch.coroutine.BaseCoroutineComponent
import com.divinkas.app.githubmodule.arch.coroutine.CoroutineComponent
import com.divinkas.app.githubmodule.arch.localdata.LocalStorage
import com.divinkas.app.githubmodule.arch.network.BaseHttpCommunicationComponent
import com.divinkas.app.githubmodule.arch.network.HttpCommunicationComponent
import com.divinkas.app.githubmodule.components.RepositoryLocalComponent
import com.divinkas.app.githubmodule.components.RepositoryLocalComponentImpl
import com.divinkas.app.githubmodule.components.RepositoryWebComponent
import com.divinkas.app.githubmodule.components.RepositoryWebComponentImpl
import com.divinkas.app.githubmodule.configuration.ConfigUtils
import org.koin.core.context.loadKoinModules
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class KoinInstaller(
    private val configUtils: ConfigUtils
) {

    private val providerModules = module {
        single { configUtils }
    }

    private val components = module {
        single { LocalStorage(get()) }
        single { GitHubModule(get(), get()) }
        single<HttpCommunicationComponent> { BaseHttpCommunicationComponent(get()) }
        single<CoroutineComponent> { BaseCoroutineComponent() }
        single<RepositoryWebComponent> { RepositoryWebComponentImpl(get(), get()) }
        single<RepositoryLocalComponent> { RepositoryLocalComponentImpl(get()) }
    }

    fun install() {
        koinApplication {
            loadKoinModules(listOf(providerModules, components))
        }
    }
}