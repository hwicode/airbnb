package com.example.airbnb.data.tmap


import com.google.gson.annotations.SerializedName

data class TmapTimeDto(
    @SerializedName("features")
    val features: List<Feature>?,
)

data class Feature(
    @SerializedName("properties")
    val properties: Properties,
)

data class Properties(
    @SerializedName("totalTime")
    val totalTime: Int,
)

fun TmapTimeDto.toTime(): Int {
    this.features?.let {
        return it[0].properties.totalTime.div(60)
    } ?: return 5
}