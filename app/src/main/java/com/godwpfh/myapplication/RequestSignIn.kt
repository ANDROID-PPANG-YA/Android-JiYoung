package com.godwpfh.myapplication

import com.google.gson.annotations.SerializedName

data class RequestSignIn(
    val email:String,
    val password: String
)
