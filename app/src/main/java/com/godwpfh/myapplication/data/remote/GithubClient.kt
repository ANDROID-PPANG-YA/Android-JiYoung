package com.godwpfh.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService : GithubService = retrofit.create(GithubService::class.java)
}