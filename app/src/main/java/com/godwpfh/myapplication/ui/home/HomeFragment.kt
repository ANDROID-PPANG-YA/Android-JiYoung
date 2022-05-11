package com.godwpfh.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godwpfh.myapplication.databinding.FragmentHomeBinding
import com.godwpfh.myapplication.adapter.TabProfileAdpater
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var  _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!
    private lateinit var homeTabViewPagerAdpater : TabProfileAdpater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter(){
        val fragmentList= listOf(TabHomeFollowerFragment(), TabHomeFollowingFragment())
        homeTabViewPagerAdpater= TabProfileAdpater(this)
        homeTabViewPagerAdpater.setFragments(fragmentList)

        binding.fragmentHomeViewpager.adapter=homeTabViewPagerAdpater
    }

    private fun initTabLayout(){
        val tabLabel= listOf("팔로워","팔로잉")
        TabLayoutMediator(binding.fragmentHomeTablayout, binding.fragmentHomeViewpager){ tab , position ->
            tab.text=tabLabel[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}