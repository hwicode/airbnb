package com.example.airbnb.data.remote.personal

import com.example.airbnb.common.AccessToken
import com.example.airbnb.data.dto.ReservationDto
import com.example.airbnb.data.dto.WishDetailDto

class PersonalRemoteDataSource(private val api: PersonalApi):PerosnalDataSource {
    override suspend fun getWishList(): WishDetailDto {
        return api.getWishList("Bearer ${AccessToken.JWT}")
    }

    override suspend fun getReservationList(): ReservationDto {
        return api.getReservationList("Bearer ${AccessToken.JWT}")
    }
}