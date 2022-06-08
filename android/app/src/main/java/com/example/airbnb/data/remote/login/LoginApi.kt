package com.example.airbnb.data.remote.login

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("api/oauth/callback/")
    suspend fun getJWT(@Query("code") code: String): ResponseBody
}



