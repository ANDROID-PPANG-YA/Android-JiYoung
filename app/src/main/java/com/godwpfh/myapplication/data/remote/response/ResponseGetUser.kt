package com.godwpfh.myapplication.data.remote.response

data class ResponseGetUser(
    val status: Int,
    val message: String,
    val data: Data
)
{
    data class Data(
        val login : String,
        val name: String,
        val bio: String,
        val avatar_url : String
    )
}