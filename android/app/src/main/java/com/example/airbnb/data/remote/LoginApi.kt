package com.example.airbnb.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("api/oauth/login/github/")
    fun getJWT(@Query("code") code:String ){

    }

}