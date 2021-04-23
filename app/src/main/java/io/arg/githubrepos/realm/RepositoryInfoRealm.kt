package io.arg.githubrepos.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RepositoryInfoRealm: RealmObject() {

    @PrimaryKey
    var id: String = ""
    var commits: RealmList<CommitInfoRealm> = RealmList()
}