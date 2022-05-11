package com.godwpfh.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val followFragment = FollowFragment()
    private val reposFragment = ReposFragment()

    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        viewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        initTransaction()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initTransaction() {
        //처음에 보여질 때 follow로 뜨게
        childFragmentManager.beginTransaction()
            .add(R.id.profile_fragmentview, followFragment)
            .commit()

        binding.homeFollowBtn.isSelected = true
        binding.homeReposBtn.isSelected = false


        binding.homeFollowBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, followFragment)
                .commit()

            binding.homeFollowBtn.isSelected = true
            binding.homeReposBtn.isSelected = false
        }

        binding.homeReposBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, reposFragment)
                .commit()
            binding.homeReposBtn.isSelected = true
            binding.homeFollowBtn.isSelected = false
        }
    }
}