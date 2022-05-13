package com.godwpfh.myapplication.data.remote

import com.godwpfh.myapplication.data.remote.request.RequestSignIn
import com.godwpfh.myapplication.data.remote.request.RequestSignUp
import com.godwpfh.myapplication.data.remote.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postSignin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

}