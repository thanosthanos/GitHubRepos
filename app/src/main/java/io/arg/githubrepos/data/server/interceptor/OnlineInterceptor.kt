package io.arg.githubrepos.data.server.interceptor

import io.arg.githubrepos.application.GitHubRepositoryApplication.Companion.isInternetAvailable
import io.arg.githubrepos.data.server.api.GitHubRepositoryApi.CACHE_CONTROL_HEADER
import io.arg.githubrepos.data.server.api.GitHubRepositoryApi.HEADER_PRAGMA
import io.arg.githubrepos.exception.NoConnectivityException
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Online caching mechanism and throw error when internet connection is missing.
 */
class OnlineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!isInternetAvailable()) {
            throw NoConnectivityException()
        }

        val cacheControl = CacheControl.Builder()
                .maxAge(60, TimeUnit.MINUTES)
                .build()

        return response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(CACHE_CONTROL_HEADER)
                .header(CACHE_CONTROL_HEADER, cacheControl.toString())
                .build()
    }
}