package io.arg.githubrepos.data.server.api

import io.arg.githubrepos.application.GitHubRepositoryApplication.Companion.ctx
import io.arg.githubrepos.data.server.interceptor.OnlineInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Basic API with simple online caching
 */
object GitHubRepositoryApi {

    private const val baseUrl = "https://api.github.com/"
    private const val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(ctx.cacheDir, cacheSize)
    const val CACHE_CONTROL_HEADER = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"

    private val apiClient = OkHttpClient
            .Builder()
            .addInterceptor(OnlineInterceptor())
            .cache(myCache)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(apiClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRepositoryApi(): GitHubRepositoryService {
        return retrofit.create(GitHubRepositoryService::class.java)
    }

}