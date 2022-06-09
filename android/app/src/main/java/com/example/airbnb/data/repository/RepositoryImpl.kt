package com.example.airbnb.data.repository

import com.example.airbnb.data.remote.login.LoginDataSource
import com.example.airbnb.domain.Repository

class RepositoryImpl(private val loginDataSource: LoginDataSource) : Repository {

    override suspend fun getAccessToken():String{
        val token = loginDataSource.getAccessToken()
        println("토큰 발행 $token")
        return token
    }
}