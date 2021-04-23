package io.arg.githubrepos.realm

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class CommitInfoRealm: RealmObject() {

    var message: String = ""
    var sha: String = ""
    var name : String = ""
    var date: String = ""
}