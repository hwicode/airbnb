package com.example.airbnb.data.remote

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.HttpCookie

interface LoginApi {

    @GET("api/oauth/login/github/")
    suspend fun getJWT(@Query("code") code: String): Response
}



