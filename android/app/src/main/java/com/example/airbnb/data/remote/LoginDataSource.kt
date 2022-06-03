package com.example.airbnb.data.remote


interface LoginDataSource {
    suspend fun getAccessToken(): String
}