package com.godwpfh.myapplication.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private var _binding : Fragment1Binding?=null
    private val binding get() = _binding?: error("Binding이 초기화되지 않았습니다.")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=Fragment1Binding.inflate(layoutInflater, container, false)
        binding.buttonFragment1.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }
       return binding.root
    }


}