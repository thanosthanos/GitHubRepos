package io.arg.githubrepos.di

import io.arg.githubrepos.data.server.api.RepositoryApi
import org.koin.core.module.Module
import org.koin.dsl.module


val networkModule: Module = module {
    single { RepositoryApi }
}