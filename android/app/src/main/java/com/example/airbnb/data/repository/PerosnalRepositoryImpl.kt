package com.example.airbnb.data.repository

import com.example.airbnb.data.dto.toReservationItem
import com.example.airbnb.data.dto.toWishItem
import com.example.airbnb.data.remote.personal.PerosnalDataSource
import com.example.airbnb.domain.PersonalRepository
import com.example.airbnb.domain.model.ReservationItem
import com.example.airbnb.domain.model.WishItem

class PerosnalRepositoryImpl(private val personalDataSource: PerosnalDataSource) : PersonalRepository {

    override suspend fun getWishList(): List<WishItem> {
        return  personalDataSource.getWishList().map {
            it.toWishItem()
        }
    }

    override suspend fun getReservationList(): List<ReservationItem> {
        return  personalDataSource.getReservationList().map {
            it.toReservationItem()
        }
    }
}