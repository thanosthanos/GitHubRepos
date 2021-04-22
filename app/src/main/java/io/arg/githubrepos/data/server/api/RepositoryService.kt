package io.arg.githubrepos.data.server.api

import io.arg.githubrepos.data.server.model.CommitInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryService {

    @GET("repos/{owner}/{repository}")
    fun getRepositoryInfo(@Path("owner") owner: String, @Path("repository") repository: String): GitHubRepositoryInfo

    @GET("repos/{owner}/{repository}/commits")
    fun getRepositoryCommits(@Path("owner") owner: String, @Path("repository") repository: String): CommitInfo
}