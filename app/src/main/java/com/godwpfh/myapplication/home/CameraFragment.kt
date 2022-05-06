package com.godwpfh.myapplication.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)

        initCameraButton()
        setPhotoFromGallery()
        return binding.root
    }

    private fun initCameraButton() {
        binding.fragmentCameraButton.setOnClickListener {
            val intent = Intent()
            intent.apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            activityResultLauncher.launch(intent)
        }
    }

    private fun setPhotoFromGallery() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                Glide.with(this)
                    .load(result.data?.data)
                    .into(binding.fragmentCameraImageview)

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}