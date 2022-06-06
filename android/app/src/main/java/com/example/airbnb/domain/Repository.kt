package com.example.airbnb.domain

interface Repository {
    suspend fun getAccessToken():String
}