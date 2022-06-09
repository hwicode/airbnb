package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.ReservationItem
import com.google.gson.annotations.SerializedName

class ReservationDto : ArrayList<ReservationDtoItem>()

data class ReservationDtoItem(
    @SerializedName("accommodationId")
    val accommodationId: Int?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("checkIn")
    val checkIn: String,
    @SerializedName("checkOut")
    val checkOut: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("reservationId")
    val reservationId: Int,
    @SerializedName("roomName")
    val roomName: String?
)

fun ReservationDtoItem.toReservationItem():ReservationItem = ReservationItem(image?:"",roomName?:"", address?:"", checkIn, checkOut )