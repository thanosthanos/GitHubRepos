package io.arg.githubrepos.data.repository

import io.arg.githubrepos.data.server.api.GitHubRepositoryApi
import io.arg.githubrepos.data.server.model.CommitInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryResponse

class FetchGitHubInfoRepositoryImplementation(private val api: GitHubRepositoryApi) : FetchGitHubInfoRepository {

    override suspend fun fetchData(owner: String, repository: String): GitHubRepositoryResponse {

        val info: GitHubRepositoryInfo = api.getRepositoryApi().getRepositoryInfo(owner = owner, repository = repository)
        val commits : List<CommitInfo> = api.getRepositoryApi().getRepositoryCommits(owner = owner, repository = repository)

        return GitHubRepositoryResponse(info, ArrayList<CommitInfo>())
    }


}