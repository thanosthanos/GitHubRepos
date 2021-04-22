package io.arg.githubrepos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.arg.githubrepos.R
import io.arg.githubrepos.databinding.ActivityMainBinding
import io.arg.githubrepos.ui.fragment.SearchRepositoryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addSearchReposFragment()
    }

    private fun addSearchReposFragment() {
        val searchRepositoryFragment = SearchRepositoryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.search_repos_fragment, searchRepositoryFragment)
        transaction.commit()
    }

}