package com.godwpfh.myapplication.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentFollowBinding
import com.godwpfh.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var  _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(layoutInflater, container, false)


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}