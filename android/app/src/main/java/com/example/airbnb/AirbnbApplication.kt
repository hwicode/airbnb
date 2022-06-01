package com.example.airbnb

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class AirbnbApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AirbnbApplication)
            module {

            }
        }

    }
}