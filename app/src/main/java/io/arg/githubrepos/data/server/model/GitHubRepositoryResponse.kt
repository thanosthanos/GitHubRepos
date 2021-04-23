package io.arg.githubrepos.data.server.model

data class GitHubRepositoryResponse(val info: GitHubRepositoryInfo, val commits: List<GeneralCommitInfo>)