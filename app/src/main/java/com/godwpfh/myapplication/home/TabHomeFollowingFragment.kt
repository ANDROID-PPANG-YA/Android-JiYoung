package com.godwpfh.myapplication.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentTabHomeFollowerBinding
import com.godwpfh.myapplication.databinding.FragmentTabHomeFollowingBinding

class TabHomeFollowingFragment : Fragment() {

    private var _binding: FragmentTabHomeFollowingBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentTabHomeFollowingBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}