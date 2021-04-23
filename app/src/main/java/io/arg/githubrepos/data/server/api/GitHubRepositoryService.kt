package io.arg.githubrepos.data.server.api

import io.arg.githubrepos.data.server.model.CommitInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRepositoryService {

    @GET("repos/{owner}/{repository}")
    suspend fun getRepositoryInfo(@Path("owner") owner: String, @Path("repository") repository: String): GitHubRepositoryInfo

    @GET("repos/{owner}/{repository}/commits")
    suspend fun getRepositoryCommits(@Path("owner") owner: String, @Path("repository") repository: String): List<CommitInfo>
}