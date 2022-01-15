package com.test.openweather

import android.app.Application
import com.test.openweather.di.dataModule
import com.test.openweather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(dataModule)
            modules(viewModelModule)
        }
    }
}