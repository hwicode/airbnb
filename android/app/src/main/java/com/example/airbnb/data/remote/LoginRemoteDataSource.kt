package com.example.airbnb.data.remote

import com.example.airbnb.common.Constants

class LoginRemoteDataSource(private val api:LoginApi) : LoginDataSource{
    override suspend fun getAccessToken(): List<String> {
        return api.getJWT(Constants.CODE).headers.values("Set-Cookies")
    }
}