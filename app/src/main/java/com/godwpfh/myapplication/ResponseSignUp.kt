package com.godwpfh.myapplication

data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data
)
{
    data class Data(
        val name:String,
        val email:String
    )
}