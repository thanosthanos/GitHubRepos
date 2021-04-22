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

class SearchRepositoryFragment: Fragment() {

    private lateinit var binding: FragmentSearchReposBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentSearchReposBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {

        binding.searchButton.setOnClickListener {
            // TODO view model
        }

        addShowRepositoryInfoFragment()
    }

    private fun addShowRepositoryInfoFragment() {
        val showRepositoryInfoFragment = ShowRepositoryInfoFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.showRepositoryInfoLayout, showRepositoryInfoFragment)
        transaction.commit()
    }

    private fun showLoadingView() {
        binding.progressBar.visibility = VISIBLE
        binding.showRepositoryInfoLayout.visibility = GONE
    }

    private fun showRepositoryDetails() {
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