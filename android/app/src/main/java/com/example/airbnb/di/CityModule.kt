package com.example.airbnb.di

import com.example.airbnb.common.Constants
import com.example.airbnb.data.remote.city.CityApi
import com.example.airbnb.data.remote.city.CityDataSource
import com.example.airbnb.data.remote.city.CityRemoteDataSource
import com.example.airbnb.domain.city.CityRepository
import com.example.airbnb.domain.city.CityRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val CityModule = module {

    single<Retrofit>(named("CityRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(named("Normal")))
            .build()
    }

    single<CityApi> {
        get<Retrofit>(named("CityRetrofit")).create(CityApi::class.java)
    }
    single<CityDataSource> { CityRemoteDataSource(get()) }
    single<CityRepository> { CityRepositoryImpl(get()) }


}