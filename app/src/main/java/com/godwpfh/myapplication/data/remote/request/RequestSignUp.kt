package com.godwpfh.myapplication.data.remote.request

import com.google.gson.annotations.SerializedName

data class RequestSignUp(
    val name: String,
    val email: String,
    val password: String
)
