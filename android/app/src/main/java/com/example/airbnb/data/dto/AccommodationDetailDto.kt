package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.AccommodationDetailItem
import com.google.gson.annotations.SerializedName

data class AccommodationDetailDto(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("accommodationOptionLines")
    val accommodationOptionLines: List<AccommodationOptionLine>,
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
    val comments: Any?,
    @SerializedName("description")
    val description: String,
    @SerializedName("hostId")
    val hostId: Int,
    @SerializedName("hostName")
    val hostName: String,
    @SerializedName("hostProfileImage")
    val hostProfileImage: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("mainImageUrl")
    val mainImageUrl: String,
    @SerializedName("maxNumberOfPeople")
    val maxNumberOfPeople: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Double
)


data class AccommodationOptionLine(
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)

data class AmenityCategories(
    @SerializedName("HEATING_COOLING")
    val hEATINGCOOLING: List<HEATINGCOOLING>,
    @SerializedName("KITCHEN")
    val kITCHEN: List<KITCHEN>
)

data class HEATINGCOOLING(
    @SerializedName("amenityCategoryId")
    val amenityCategoryId: Int,
    @SerializedName("amenityType")
    val amenityType: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String
)

data class KITCHEN(
    @SerializedName("amenityCategoryId")
    val amenityCategoryId: Int,
    @SerializedName("amenityType")
    val amenityType: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String
)

fun AccommodationDetailDto.toAccommodationDetailItem(): AccommodationDetailItem =
    AccommodationDetailItem(
        accommodationId,
        accommodationType,
        address,
        averageRating,
        accommodationOptionLines,
        bathRooms,
        bedRooms,
        beds,
        commentSize,
        comments,
        description,
        hostId,
        hostName,
        hostProfileImage,
        images,
        mainImageUrl,
        maxNumberOfPeople,
        name,
        oneDayPerPrice.toInt()
    )
