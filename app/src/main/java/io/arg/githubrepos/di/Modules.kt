package io.arg.githubrepos.di

import io.arg.githubrepos.data.repository.FetchGitHubInfoRepositoryImplementation
import io.arg.githubrepos.data.server.api.GitHubRepositoryApi
import io.arg.githubrepos.viewmodel.GitHubInformationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { GitHubInformationViewModel(get()) }
}

val repositoryModule: Module = module {
    single { FetchGitHubInfoRepositoryImplementation(get()) }
}

val networkModule: Module = module {
    single { GitHubRepositoryApi }
}