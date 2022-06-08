package com.example.airbnb.di

import com.example.airbnb.common.Constants
import com.example.airbnb.data.repository.RepositoryImpl
import com.example.airbnb.data.remote.login.LoginApi
import com.example.airbnb.data.remote.login.LoginDataSource
import com.example.airbnb.data.remote.login.LoginRemoteDataSource
import com.example.airbnb.data.remote.searchResult.SearchDataSource
import com.example.airbnb.data.remote.searchResult.SearchRemoteDataSource
import com.example.airbnb.data.remote.searchResult.SearchResultApi
import com.example.airbnb.data.repository.SearchRepositoryImpl
import com.example.airbnb.domain.Repository
import com.example.airbnb.domain.SearchRepository
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetWorkModule = module {

    single{ OkHttpClient.Builder().addInterceptor(get<Interceptor>(named("Interceptor"))).build() }

    single<Interceptor>(named("Interceptor")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Retrofit>(named("LoginRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.WEBVIEW_LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))

            .client(get())
            .build()
    }

    single<Retrofit>(named("SearchResultRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.MOCK_API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(get())
            .build()
    }



    single<LoginApi> {
        get<Retrofit>(named("LoginRetrofit")).create(LoginApi::class.java)
    }
    single<LoginDataSource> { LoginRemoteDataSource(get()) }
    single<Repository> { RepositoryImpl(get()) }


    single<SearchResultApi>{
        get<Retrofit>(named("SearchResultRetrofit")).create(SearchResultApi::class.java)
    }
    single<SearchDataSource>{ SearchRemoteDataSource(get())}
    single<SearchRepository>{ SearchRepositoryImpl(get())}
}