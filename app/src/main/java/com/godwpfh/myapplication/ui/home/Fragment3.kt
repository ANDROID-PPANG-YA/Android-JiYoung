package com.godwpfh.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.Fragment3Binding
import com.godwpfh.myapplication.ui.auth.SignInActivity

class Fragment3 : Fragment() {
    private var _binding: Fragment3Binding?=null
    private val binding get() = _binding?: error("binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= Fragment3Binding.inflate(layoutInflater, container, false)

        binding.buttonFragment3.setOnClickListener {
            startActivity(Intent(activity, SignInActivity::class.java))
        }

        return binding.root
    }
}