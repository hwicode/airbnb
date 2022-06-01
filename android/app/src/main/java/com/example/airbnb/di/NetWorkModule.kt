package com.codesquad.starbucks.di

import com.example.airbnb.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetWorkModule = module {

    single{ OkHttpClient.Builder().build() }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Retrofit>(named("LoginRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.WEBVIEW_LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

}