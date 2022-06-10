package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.WishItem
import com.google.gson.annotations.SerializedName

class WishDetailDto : ArrayList<WishDetailDtoItem>()

data class WishDetailDtoItem(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("commentCount")
    val commentCount: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Int,
    @SerializedName("roomName")
    val roomName: String
)

fun WishDetailDtoItem.toWishItem():WishItem = WishItem(image, true, roomName, averageRating.toFloat(), commentCount , oneDayPerPrice)