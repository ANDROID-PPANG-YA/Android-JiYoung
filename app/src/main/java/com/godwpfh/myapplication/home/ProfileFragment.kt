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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentProfileBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}