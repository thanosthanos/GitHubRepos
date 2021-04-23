package io.arg.githubrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.arg.githubrepos.data.repository.FetchGitHubInfoRepositoryImplementation
import io.arg.githubrepos.data.resource.Resource
import io.arg.githubrepos.data.resource.Resource.Failure
import io.arg.githubrepos.data.resource.Resource.Loading
import kotlinx.coroutines.Dispatchers

class GitHubInformationViewModel(private val repository: FetchGitHubInfoRepositoryImplementation) : ViewModel() {

    fun getRepositoryInfo(owner: String, repositoryString: String) = liveData(Dispatchers.IO) {
        emit(Loading(partialData = null))
        try {
            emit(Resource.Success(data = repository.fetchData(owner = owner, repository = repositoryString)))
        } catch (exception: Exception) {
            emit(Failure(exception))
        }
    }

}