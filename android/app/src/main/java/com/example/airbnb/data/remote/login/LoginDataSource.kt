package com.example.airbnb.data.remote.login


interface LoginDataSource {
    suspend fun getAccessToken(): String
}