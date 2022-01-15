package com.test.openweather.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("daily")
    val daily : List<Daily> = listOf()
){
    data class Daily(
        @SerializedName("pressure")
        val pressure : Float? = null,
        @SerializedName("humidity")
        val humidity : Float? = null,
        @SerializedName("wind_speed")
        val wind_speed : Float? = null,
        @SerializedName("weather")
        val weather : List<Weather> = listOf(),
        @SerializedName("temp")
        val temp : Temp? = null,
    )

    data class Weather(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("main")
        val main: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("icon")
        val icon: String? = null,
    )

    data class Temp(
        @SerializedName("day")
        val day : Float? = null,
        @SerializedName("min")
        val min : Float? = null,
        @SerializedName("max")
        val max : Float? = null
    )

}