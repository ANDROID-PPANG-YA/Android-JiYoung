package com.godwpfh.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= Fragment2Binding.inflate(layoutInflater, container, false)
        binding.buttonFragment2.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }
        return binding.root
    }

}