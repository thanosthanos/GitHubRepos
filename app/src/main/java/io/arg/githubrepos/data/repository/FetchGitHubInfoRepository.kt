package io.arg.githubrepos.data.repository

import io.arg.githubrepos.data.server.model.GitHubRepositoryResponse

interface FetchGitHubInfoRepository {
    suspend fun fetchData(owner: String, repository: String): GitHubRepositoryResponse
}