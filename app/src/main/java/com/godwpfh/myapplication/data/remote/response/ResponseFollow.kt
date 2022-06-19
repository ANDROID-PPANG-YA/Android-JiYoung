package com.godwpfh.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseFollow(
    @SerializedName("avatar_url")
    val image: String,
    @SerializedName("login")
    val name : String
)