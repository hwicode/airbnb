package com.example.airbnb.data.remote.personal

import com.example.airbnb.data.dto.ReservationDto
import com.example.airbnb.data.dto.WishDetailDto
import retrofit2.http.GET
import retrofit2.http.Header

interface PersonalApi {

    @GET("members")
    suspend fun getWishList(@Header("Authorization") token:String): WishDetailDto

    @GET("reservations")
    suspend fun getReservationList(@Header("Authorization") token: String): ReservationDto
}