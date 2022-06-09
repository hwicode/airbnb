package com.example.airbnb.data.remote.login

import android.util.Log
import com.example.airbnb.common.AccessToken

class LoginRemoteDataSource(private val api: LoginApi) : LoginDataSource {
    override suspend fun getAccessToken(): String {
        val token = api.getJWT(AccessToken.CODE)
        Log.d("토큰쿠키확인", "token.source()")
        return token.toString()
    }
}