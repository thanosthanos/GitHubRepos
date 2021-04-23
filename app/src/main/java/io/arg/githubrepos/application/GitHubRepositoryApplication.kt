package io.arg.githubrepos.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.arg.githubrepos.di.networkModule
import io.arg.githubrepos.di.repositoryModule
import io.arg.githubrepos.di.viewModelModule
import io.arg.githubrepos.realm.RepoInfoModule
import io.realm.Realm
import io.realm.RealmConfiguration
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
        initRealm()
        startKoin()
    }

    private fun initRealm() {

        // Configure Realm
        Realm.init(ctx)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("repoinfo.realm")
            .schemaVersion(1)
            .modules(RepoInfoModule())
            .compactOnLaunch()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun startKoin() {

        startKoin {
            androidContext(ctx)
            val modules = listOf(viewModelModule, repositoryModule, networkModule)
            modules(modules)
        }
    }

}