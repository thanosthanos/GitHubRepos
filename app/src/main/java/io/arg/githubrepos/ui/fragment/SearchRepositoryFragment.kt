package io.arg.githubrepos.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.arg.githubrepos.R
import io.arg.githubrepos.data.server.model.GeneralCommitInfo
import io.arg.githubrepos.data.server.model.GitHubRepositoryResponse
import io.arg.githubrepos.databinding.FragmentSearchReposBinding
import io.arg.githubrepos.exception.NoConnectivityException
import io.arg.githubrepos.ui.adapter.RepositoriesCommitsAdapter
import io.arg.githubrepos.viewmodel.GitHubInformationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRepositoryFragment: Fragment() {

    private lateinit var binding: FragmentSearchReposBinding
    private val viewModel by viewModel<GitHubInformationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSearchReposBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        binding.searchButton.setOnClickListener {
            searchRepo(term = binding.searchRepoEditText.text.toString())
        }
    }

    private fun searchRepo(term: String) {
        val valuesArray = term.split("/").toTypedArray()
        if(valuesArray.size != 2) {
            Toast.makeText(context, getString(R.string.error_wrong_format), Toast.LENGTH_LONG).show()
            return
        }

        getData(valuesArray[0], valuesArray[1])
    }

    private fun getData(owner: String, repository: String) {

        viewModel.getRepositoryInfo(owner = owner, repositoryString = repository).observe(viewLifecycleOwner, androidx.lifecycle.Observer { resource ->
            run {
                resource.onLoading {
                    showLoadingView(show = true)
                }
                .onSuccess { response ->
                    showRepositoryDetails(response = response)
                }
                .onFailure { error: Throwable ->
                    showError(error = error)
                }
            }
        })

    }

    private fun showLoadingView(show: Boolean) {
        if(show) {
            binding.progressBar.visibility = VISIBLE
            binding.viewGroup.visibility = GONE
        } else {
            binding.progressBar.visibility = GONE
            binding.viewGroup.visibility = VISIBLE
        }
    }

    private fun showRepositoryDetails(response: GitHubRepositoryResponse) {
        showLoadingView(show = false)

        binding.repoTitleTextView.text = response.info.id
        setAdapter(list = response.commits)
    }

    private fun setAdapter(list: List<GeneralCommitInfo>) {
        val adapter = RepositoriesCommitsAdapter(context = requireContext(), commitsList = list)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun showError(error: Throwable) {
        binding.progressBar.visibility = GONE
        binding.viewGroup.visibility = GONE

        when (error) {
            is NoConnectivityException -> {
                Toast.makeText(context, getString(R.string.error_no_internet), Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(context, getString(R.string.error_general), Toast.LENGTH_LONG).show()
            }
        }
    }

}