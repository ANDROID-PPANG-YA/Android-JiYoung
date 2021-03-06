package com.godwpfh.myapplication.data.remote

import com.godwpfh.myapplication.data.remote.response.ResponseFollow
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

    @GET("users/{username}/following")
    fun getFollowings(@Path("username") username:String) : Call<List<ResponseFollow>>

}