package com.godwpfh.myapplication.data.remote

import com.godwpfh.myapplication.data.remote.response.ResponseGetUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username")username : String) : Call<ResponseGetUser>

}