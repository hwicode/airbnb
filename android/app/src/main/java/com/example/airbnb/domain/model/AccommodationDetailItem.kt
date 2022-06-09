package com.example.airbnb.domain.model


data class AccommodationDetailItem(
    val accommodationId: Int,
    val accommodationType: String,
    val address: String,
    val averageRating: Double,
    val bathRooms: Int,
    val bedRooms: Int,
    val beds: Int,
    val commentSize: Int,
    val comments: Any?,
    val description: String,
    val hostId: Int,
    val hostName: String,
    val hostProfileImage: String,
    val accommodationImages: List<String>,
    val mainImageUrl: String,
    val maxNumberOfPeople: Int,
    val name: String,
    val oneDayPerPrice: Int
)
