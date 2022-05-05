package com.godwpfh.myapplication.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding?=null
    private val binding  get() = _binding!!

    private  val followFragment = FollowFragment()
    private val reposFragment = ReposFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransaction()
        initFollowButton()
        initReposButton()

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun initFollowButton(){
        binding.homeFollowBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, followFragment)
                .commit()
        }
    }

    private fun initReposButton(){
        binding.homeReposBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, reposFragment)
                .commit()
        }
    }

    private fun initTransaction(){
        //처음에 보여질 때 뜨게
        childFragmentManager.beginTransaction()
            .add(R.id.profile_fragmentview, followFragment)
            .commit()
    }
}