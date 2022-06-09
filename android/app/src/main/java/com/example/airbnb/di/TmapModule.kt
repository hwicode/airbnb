package com.example.airbnb.di

import com.example.airbnb.data.tmap.TmapApi
import com.example.airbnb.data.tmap.TmapDataSource
import com.example.airbnb.data.tmap.TmapRemoteDataSource
import com.example.airbnb.domain.tmap.TmapRepository
import com.example.airbnb.domain.tmap.TmapRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val TmapModule = module {

    single<Retrofit>(named("TmapRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://apis.openapi.sk.com/tmap/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(named("Normal")))
            .build()
    }

    single<TmapApi> {
        get<Retrofit>(named("TmapRetrofit")).create(TmapApi::class.java)
    }
    single<TmapDataSource> { TmapRemoteDataSource(get()) }
    single<TmapRepository> { TmapRepositoryImpl(get()) }

}