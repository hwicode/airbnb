package com.example.airbnb.data.tmap

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TmapApi {

    @POST("routes?version=1&format=json&callback=result")
    suspend fun getTotalTimeByCar(
        @Header("appkey") appkey: String,
        @Body tmap: TmapTimeRequest
    ): TmapTimeDto

}