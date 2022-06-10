package com.example.airbnb

import android.app.Application
import com.example.airbnb.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AirbnbApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AirbnbApplication)
            modules(appModule, NetWorkModule, TmapModule, CityModule, AccommodationDetailModule)
        }
    }
}