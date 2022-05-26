package com.godwpfh.myapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGetUser(

        @SerializedName("login")
        val login : String,
        @SerializedName("name")
        val name: String,
        @SerializedName("bio")
        val bio: String,
        @SerializedName("avatar_url")
        val avatar_url : String
    )
