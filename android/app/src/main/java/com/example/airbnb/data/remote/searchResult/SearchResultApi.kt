package com.example.airbnb.data.remote.searchResult

import com.example.airbnb.data.dto.ReservationDto
import com.example.airbnb.data.dto.SearchAccommodationByConditionResultDto
import com.example.airbnb.data.dto.SearchAccommodationResultDto
import com.example.airbnb.data.dto.WishDetailDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SearchResultApi {

    @GET("accommodations/cities")
    suspend fun getAccommodationsByCity(@Query("city_name") cityName: String):SearchAccommodationResultDto


    @GET("accommodations/search")
    suspend fun getAccommodationsByAllCondition(@QueryMap condtion:Map<String,String>):SearchAccommodationByConditionResultDto

    @GET("members/devjun10")
    suspend fun getWishList():WishDetailDto

    @GET("reservations/users/devjun10")
    suspend fun getReservationList(): ReservationDto
}