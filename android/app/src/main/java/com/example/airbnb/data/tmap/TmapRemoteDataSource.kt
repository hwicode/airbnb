package com.example.airbnb.data.tmap

import com.example.airbnb.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TmapRemoteDataSource(private val tmapApi: TmapApi) : TmapDataSource {

    override suspend fun getTotalTime(tmapTimeRequest: TmapTimeRequest): Flow<TmapTimeDto> {
        return flow {
            val tmapTimeDto = tmapApi.getTotalTimeByCar(tmapTimeRequest)
            emit(tmapTimeDto)
        }
    }
}