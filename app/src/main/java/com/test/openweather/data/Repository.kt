package com.test.openweather.data

import com.test.openweather.model.ForecastResponse
import com.test.openweather.model.WeatherResponse
import com.test.openweather.remote.Api
import io.reactivex.Observable

class Repository(val api: Api) {

    fun getWeather(cityName : String, appId: String): Observable<WeatherResponse>{
        return api.getWeather(cityName, appId)
    }

    fun getForecast(lat: String, lon: String, exclude: String, appId: String): Observable<ForecastResponse>{
        return api.getForecast(lat, lon, exclude, appId)
    }
}