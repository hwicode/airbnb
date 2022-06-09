package com.example.airbnb.data.tmap

import kotlinx.coroutines.flow.Flow

interface TmapDataSource {

    suspend fun getTotalTime(tmapTimeRequest: TmapTimeRequest): Flow<TmapTimeDto>

}