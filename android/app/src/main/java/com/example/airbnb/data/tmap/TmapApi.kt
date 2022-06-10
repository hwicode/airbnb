package com.example.airbnb.data.tmap

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TmapApi {

    @POST("routes?version=1&callback=result")
    suspend fun getTotalTimeByCar(
        @Body tmap: TmapTimeRequest
    ): TmapTimeDto

}