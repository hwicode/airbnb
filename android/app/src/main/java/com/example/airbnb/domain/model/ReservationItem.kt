package com.example.airbnb.domain.model

data class ReservationItem(
    val imageUrl:String = "",
    val name:String="",
    val address:String="",
    val checkin:String="",
    val checkout:String=""
)