package com.example.airbnb.domain

import com.example.airbnb.domain.model.ReservationItem
import com.example.airbnb.domain.model.WishItem

interface PersonalRepository {

    suspend fun getWishList():List<WishItem>
    suspend fun getReservationList():List<ReservationItem>
}