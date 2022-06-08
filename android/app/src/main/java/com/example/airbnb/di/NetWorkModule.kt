package com.example.airbnb.di

import com.example.airbnb.common.Constants
import com.example.airbnb.data.RepositoryImpl
import com.example.airbnb.data.remote.LoginApi
import com.example.airbnb.data.remote.LoginDataSource
import com.example.airbnb.data.remote.LoginRemoteDataSource
import com.example.airbnb.domain.Repository
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

    single<LoginApi> {
        get<Retrofit>(named("LoginRetrofit")).create(LoginApi::class.java)
    }
    single<LoginDataSource> { LoginRemoteDataSource(get()) }
    single<Repository> { RepositoryImpl(get()) }

}