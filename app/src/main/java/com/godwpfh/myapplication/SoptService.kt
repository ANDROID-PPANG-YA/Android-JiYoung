package com.godwpfh.myapplication

import retrofit2.Call
import retrofit2.http.Body
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