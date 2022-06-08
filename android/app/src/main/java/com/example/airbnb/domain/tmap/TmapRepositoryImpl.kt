package com.example.airbnb.domain.tmap

import com.example.airbnb.data.tmap.TmapDataSource
import com.example.airbnb.data.tmap.TmapTimeRequest
import com.example.airbnb.data.tmap.toTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TmapRepositoryImpl(private val tmapData: TmapDataSource) : TmapRepository {

    override suspend fun getTotalTime(tmapTimeRequest: TmapTimeRequest): Flow<Int> {
        return tmapData.getTotalTime(tmapTimeRequest).map {
            it.toTime()
        }
    }
}