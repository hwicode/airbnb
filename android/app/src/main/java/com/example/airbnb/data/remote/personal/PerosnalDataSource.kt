package com.example.airbnb.data.remote.personal

import com.example.airbnb.data.dto.ReservationDto
import com.example.airbnb.data.dto.WishDetailDto

interface PerosnalDataSource {
    suspend fun getWishList(): WishDetailDto
    suspend fun getReservationList(): ReservationDto
}