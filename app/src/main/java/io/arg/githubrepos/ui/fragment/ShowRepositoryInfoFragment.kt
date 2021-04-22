package io.arg.githubrepos.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.arg.githubrepos.databinding.FragmentShowRepositoryInfoBinding

class ShowRepositoryInfoFragment : Fragment() {

    private lateinit var binding: FragmentShowRepositoryInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentShowRepositoryInfoBinding.inflate(inflater, container, false)

        return binding.root
    }



}