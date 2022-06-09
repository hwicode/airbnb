package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.AccommodationDetailItem
import com.google.gson.annotations.SerializedName

data class AccommodationDetailDto(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("accommodationOptionLines")
    val accommodationOptionLines: List<Any>,
    @SerializedName("accommodationType")
    val accommodationType: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("amenityCategories")
    val amenityCategories: AmenityCategories,
    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("bathRooms")
    val bathRooms: Int,
    @SerializedName("bedRooms")
    val bedRooms: Int,
    @SerializedName("beds")
    val beds: Int,
    @SerializedName("commentSize")
    val commentSize: Int,
    @SerializedName("comments")
    val comments: List<Any>,
    @SerializedName("description")
    val description: String,
    @SerializedName("hostId")
    val hostId: Int,
    @SerializedName("hostName")
    val hostName: String,
    @SerializedName("hostProfileImage")
    val hostProfileImage: String,
    @SerializedName("images")
    val accommodationImages: List<AccommodationImage>,
    @SerializedName("mainImageUrl")
    val mainImageUrl: String,
    @SerializedName("maxNumberOfPeople")
    val maxNumberOfPeople: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Int
)

data class AccommodationImage(
    @SerializedName("image")
    val image: String
)

data class AmenityCategories(
    @SerializedName("BATHROOM")
    val bATHROOM: List<String>,
    @SerializedName("FAMILY")
    val fAMILY: List<String>,
    @SerializedName("KITCHEN")
    val kITCHEN: List<String>
)


fun AccommodationDetailDto.toAccommodationDetailItem(): AccommodationDetailItem {
    return AccommodationDetailItem(
        accommodationId,
        accommodationType,
        address,
        averageRating,
        bathRooms,
        bedRooms,
        beds,
        commentSize,
        comments,
        description,
        hostId,
        hostName,
        hostProfileImage,
        accommodationImages,
        mainImageUrl,
        maxNumberOfPeople,
        name,
        oneDayPerPrice
    )
}