package com.example.airbnb.domain.tmap

import com.example.airbnb.data.tmap.TmapTimeRequest
import kotlinx.coroutines.flow.Flow

interface TmapRepository {

    suspend fun getTotalTime(tmapTimeRequest: TmapTimeRequest): Flow<Int>

}