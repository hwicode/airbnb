package com.example.airbnb.di

import com.example.airbnb.data.remote.accommodationDetail.AccommodationDetailApi
import com.example.airbnb.data.remote.accommodationDetail.AccommodationDetailDataSource
import com.example.airbnb.data.remote.accommodationDetail.AccommodationDetailRemoteDataSource
import com.example.airbnb.data.repository.AccommodationDetailRepositoryImpl
import com.example.airbnb.domain.AccommodationDetailRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val AccommodationDetailModule = module {
    single<AccommodationDetailApi> {
        get<Retrofit>(named("SearchResultRetrofit")).create(AccommodationDetailApi::class.java)
    }
    single<AccommodationDetailDataSource> { AccommodationDetailRemoteDataSource(get()) }
    single<AccommodationDetailRepository> { AccommodationDetailRepositoryImpl(get()) }
}