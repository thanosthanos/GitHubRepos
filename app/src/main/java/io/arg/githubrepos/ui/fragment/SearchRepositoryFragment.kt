package io.arg.githubrepos.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.arg.githubrepos.R
import io.arg.githubrepos.databinding.FragmentSearchReposBinding
import io.arg.githubrepos.exception.NoConnectivityException
import io.arg.githubrepos.viewmodel.GitHubInformationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.experimental.property.inject

class SearchRepositoryFragment: Fragment() {

    private lateinit var binding: FragmentSearchReposBinding
    private val viewModel by viewModel<GitHubInformationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSearchReposBinding.inflate(inflater, container, false)

        initViews()

        // TODO delete
        getData("bright", "shouldko")

        return binding.root
    }

    private fun initViews() {

        binding.searchButton.setOnClickListener {
            // TODO view model
        }

        addShowRepositoryInfoFragment()
    }

    private fun getData(owner: String, repository: String) {

        viewModel.getRepositoryInfo(owner = owner, repositoryString = repository).observe(viewLifecycleOwner, androidx.lifecycle.Observer { resource ->
            run {
                resource.onLoading {
                    showLoadingView(show = true)
                }
                .onSuccess { repositoryInfo ->
                    showRepositoryDetails()
                }
                .onFailure { error: Throwable ->
                    showError(error = error)
                }
            }
        })

    }

    private fun addShowRepositoryInfoFragment() {
        val showRepositoryInfoFragment = ShowRepositoryInfoFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.showRepositoryInfoLayout, showRepositoryInfoFragment)
        transaction.commit()
    }

    private fun showLoadingView(show: Boolean) {
        if(show) {
            binding.progressBar.visibility = VISIBLE
            binding.showRepositoryInfoLayout.visibility = GONE
        } else {
            binding.progressBar.visibility = GONE
            binding.showRepositoryInfoLayout.visibility = VISIBLE
        }
    }

    private fun showRepositoryDetails() {
        showLoadingView(show = false)

        // TODO
    }

    private fun showError(error: Throwable) {
        binding.progressBar.visibility = GONE
        binding.showRepositoryInfoLayout.visibility = GONE

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