package com.godwpfh.myapplication.data.remote.request

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    val email:String,
    val password: String
)
