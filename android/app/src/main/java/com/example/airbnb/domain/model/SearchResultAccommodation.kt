package com.example.airbnb.domain.model

data class SearchResultAccommodation(
    val imageUrl:String = "",
    val superHost: Boolean = true,
    val name:String="",
    val reviewAverage: Float = 0.0F,
    val reviewCount : Int = 0,
    val payPerNight :Int = 0,
):SearchResult()
