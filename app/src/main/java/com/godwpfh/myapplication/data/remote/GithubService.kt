package com.godwpfh.myapplication.data.remote

import com.godwpfh.myapplication.data.remote.response.ResponseGetUser
import com.godwpfh.myapplication.data.remote.response.ResponseRepos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username")username: String) : Call<ResponseGetUser>

    @GET("users/{username}/repos")
    fun getRepos(@Path("username")username: String): Call<List<ResponseRepos>>

//    @GET("users/wlwpfh/following")

}