package io.arg.githubrepos.realm

import io.arg.githubrepos.data.server.model.GitHubRepositoryResponse
import io.realm.Realm
import io.realm.RealmList

class RealmHelper {

    companion object {

        fun mapToRealm(gitHubRepositoryResponse: GitHubRepositoryResponse): RepositoryInfoRealm {
            val repositoryInfoRealm = RepositoryInfoRealm()

            repositoryInfoRealm.id = gitHubRepositoryResponse.info.id

            val commitsRealm: RealmList<CommitInfoRealm> = RealmList()
            val commits = gitHubRepositoryResponse.commits
            for(commit in commits) {
                val commitRealm = CommitInfoRealm()
                commitRealm.date = commit.date
                commitRealm.message = commit.message
                commitRealm.sha = commit.sha
                commitRealm.name = commit.name

                commitsRealm.add(commitRealm)
            }

            repositoryInfoRealm.commits = commitsRealm

            return repositoryInfoRealm
        }

        fun insertGutHubReposIntoRealm(repositoryInfoRealm: RepositoryInfoRealm) {

            Realm.getDefaultInstance().executeTransactionAsync { realm ->
                realm.insertOrUpdate(repositoryInfoRealm)
            }
        }

    }

}