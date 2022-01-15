package com.test.openweather.remote

import com.test.openweather.model.ForecastResponse
import com.test.openweather.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather?")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appId: String
    ): Observable<WeatherResponse>

    @GET("onecall?")
    fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("appid") appId:String
    ): Observable<ForecastResponse>
}