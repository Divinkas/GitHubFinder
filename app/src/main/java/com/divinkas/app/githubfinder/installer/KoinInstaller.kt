package com.divinkas.app.githubfinder.installer

import android.app.Application
import com.divinkas.app.githubfinder.ui.GitHubViewModel
import com.divinkas.app.githubfinder.ui.detail.DetailRepositoryViewModel
import com.divinkas.app.githubfinder.ui.find.FindViewModel
import com.divinkas.app.githubfinder.ui.saved.SavedRepositoryViewModel
import com.divinkas.app.githubmodule.GitHubModuleAPI
import com.divinkas.app.githubmodule.installer.ApplicationInstaller
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinInstaller : ApplicationInstaller {
    private val appModule = module {
        single { GitHubModuleAPI.instance }
    }

    private val viewModelModule = module {
        viewModel { GitHubViewModel() }
        viewModel { FindViewModel() }
        viewModel { DetailRepositoryViewModel() }
        viewModel { SavedRepositoryViewModel() }
    }

    override fun install(application: Application) {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(appModule)
            modules(viewModelModule)
        }
    }
}