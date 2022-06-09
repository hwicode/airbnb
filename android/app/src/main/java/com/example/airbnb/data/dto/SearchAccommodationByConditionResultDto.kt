package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.SearchResultAccommodation
import com.google.gson.annotations.SerializedName

class SearchAccommodationByConditionResultDto : ArrayList<SearchAccommodationByConditionResultDtoItem>()

data class SearchAccommodationByConditionResultDtoItem(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("commentCount")
    val commentCount: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Int,
    @SerializedName("roomName")
    val roomName: String
)

fun SearchAccommodationByConditionResultDtoItem.toSearchResultAccommodation(): SearchResultAccommodation = SearchResultAccommodation(imageUrl,true, roomName,averageRating.toFloat(), commentCount, oneDayPerPrice  )