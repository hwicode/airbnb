package com.example.airbnb.data.repository

import com.example.airbnb.data.remote.login.LoginDataSource
import com.example.airbnb.domain.Repository

class RepositoryImpl(private val loginDataSource: LoginDataSource) : Repository {

    override suspend fun getAccessToken():String{
        return loginDataSource.getAccessToken()
    }
}