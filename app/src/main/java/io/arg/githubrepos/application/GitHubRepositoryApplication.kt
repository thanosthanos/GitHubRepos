package io.arg.githubrepos.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.arg.githubrepos.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubRepositoryApplication: Application() {

    companion object {
        lateinit var ctx: Context

        fun isInternetAvailable(): Boolean {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetwork != null
        }
    }

    override fun onCreate() {
        super.onCreate()

        ctx = applicationContext
        startKoin()
    }

    private fun startKoin() {

        startKoin {
            androidContext(ctx)
            val modules = listOf(networkModule)
            modules(modules)
        }
    }

}