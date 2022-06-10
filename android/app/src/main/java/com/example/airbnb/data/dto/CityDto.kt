package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.CityInfo
import com.google.gson.annotations.SerializedName

class CityDto : ArrayList<CityDtoItem>()

data class CityDtoItem(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
)

fun CityDtoItem.toCityInfo(): CityInfo {
    return CityInfo(
        image,
        latitude,
        longitude,
        name,
    )
}