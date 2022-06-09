package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.SearchResultAccommodation
import com.google.gson.annotations.SerializedName

class SearchAccommodationResultDto : ArrayList<SearchAccommodationResultDtoItem>()


data class SearchAccommodationResultDtoItem(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("accommodationType")
    val accommodationType: String?,
    @SerializedName("address")
    val address: String,
    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("commentNumber")
    val commentNumber: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Int,
    @SerializedName("roomName")
    val roomName: String
)

fun SearchAccommodationResultDtoItem.toSearchResultAccommodation():SearchResultAccommodation = SearchResultAccommodation(accommodationId, image,true, roomName,averageRating.toFloat(), commentNumber, oneDayPerPrice  )