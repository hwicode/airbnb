package com.example.airbnb.data.remote.login

import com.example.airbnb.common.AccessToken

class LoginRemoteDataSource(private val api: LoginApi) : LoginDataSource {
    override suspend fun getAccessToken(): String {
        return api.getJWT(AccessToken.CODE)
    }
}