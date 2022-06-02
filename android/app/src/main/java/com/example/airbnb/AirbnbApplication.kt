package com.example.airbnb

import android.app.Application
import com.codesquad.starbucks.di.NetWorkModule
import com.codesquad.starbucks.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class AirbnbApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AirbnbApplication)
            modules(appModule, NetWorkModule)
        }

    }
}