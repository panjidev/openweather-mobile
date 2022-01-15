package com.test.openweather.di

import com.test.openweather.data.Repository
import com.test.openweather.remote.RetrofitClient
import org.koin.dsl.module

val dataModule = module {
    single{
        RetrofitClient.instance
    }
    factory {
        Repository(get())
    }
}