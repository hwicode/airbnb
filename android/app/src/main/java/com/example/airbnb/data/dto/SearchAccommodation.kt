package com.example.airbnb.data.dto


import com.example.airbnb.domain.model.SearchResult.SearchResultAccommodation
import com.google.gson.annotations.SerializedName

data class SearchAccommodation(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("numberOfElements")
    val numberOfElements: Int,
    @SerializedName("pageable")
    val pageable: Pageable,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sort: SortX
)

data class Sort(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)


data class SortX(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)

data class Pageable(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("paged")
    val paged: Boolean,
    @SerializedName("sort")
    val sort: Sort,
    @SerializedName("unpaged")
    val unpaged: Boolean
)

data class Content(
    @SerializedName("accommodationId")
    val accommodationId: Int,
    @SerializedName("accommodationType")
    val accommodationType: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("averageRating")
    val averageRating: Double,
    @SerializedName("commentNumber")
    val commentNumber: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("oneDayPerPrice")
    val oneDayPerPrice: Double,
    @SerializedName("roomName")
    val roomName: String
)

fun Content.toSearchResultAccommodation(): SearchResultAccommodation = SearchResultAccommodation(accommodationId, image,true, roomName,averageRating.toFloat(), commentNumber, oneDayPerPrice.toInt(), address  )