package com.godwpfh.myapplication.ui.home

import androidx.lifecycle.ViewModel
import com.godwpfh.myapplication.R

class HomeViewModel: ViewModel() {
    private val name="양지영"
    private val email="wlwpfh"
    private val image= R.drawable.home_image

    fun getName() = name
    fun getEmail() = email
    fun getImage() = image

}