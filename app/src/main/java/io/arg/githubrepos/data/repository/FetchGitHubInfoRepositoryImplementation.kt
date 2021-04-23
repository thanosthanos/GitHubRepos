package io.arg.githubrepos.data.repository

import io.arg.githubrepos.data.server.api.GitHubRepositoryApi
import io.arg.githubrepos.data.server.model.CommitInfo
import io.arg.githubrepos.data.server.model.GeneralCommitInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryResponse
import io.arg.githubrepos.realm.RealmHelper.Companion.insertGutHubReposIntoRealm
import io.arg.githubrepos.realm.RealmHelper.Companion.mapToRealm

class FetchGitHubInfoRepositoryImplementation(private val api: GitHubRepositoryApi) : FetchGitHubInfoRepository {

    override suspend fun fetchData(owner: String, repository: String): GitHubRepositoryResponse {

        val info: GitHubRepositoryInfo = api.getRepositoryApi().getRepositoryInfo(owner = owner, repository = repository)
        val commits : List<CommitInfo> = api.getRepositoryApi().getRepositoryCommits(owner = owner, repository = repository)
        val commitInfoList = ArrayList<GeneralCommitInfo>()
        for(commitInfo in commits) {
            commitInfoList.add(GeneralCommitInfo(message = commitInfo.commit.message, sha = commitInfo.sha, name = commitInfo.commit.author.name, date = commitInfo.commit.author.date))
        }
        commitInfoList.sortedByDescending { it.date }

        val response = GitHubRepositoryResponse(info, commitInfoList)

        // Save results into realm
        insertGutHubReposIntoRealm(mapToRealm(response))

        return response
    }


}