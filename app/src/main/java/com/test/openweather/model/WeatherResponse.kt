package com.test.openweather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id")
    val id : String? = null,
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("cod")
    val cod : String? = null,
    @SerializedName("coord")
    val coord : Coord? = null,
    @SerializedName("weather")
    val weather : List<Weather> = listOf(),
    @SerializedName("main")
    val main: Main? = null,
    @SerializedName("sys")
    val sys: Sys? = null,
) {
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
    data class Main(
        @SerializedName("temp")
        val temp: Float? = null,
        @SerializedName("feels_like")
        val feels_like : Float? = null,
        @SerializedName("temp_min")
        val temp_min : Float? = null,
        @SerializedName("temp_max")
        val temp_max : Float? = null,
        @SerializedName("pressure")
        val pressure : Int? = null,
        @SerializedName("humidity")
        val humidity : Int? = null,
    )

    data class Sys(
        @SerializedName("country")
        val country: String? = null,
    )

    data class Coord(
        @SerializedName("lon")
        val lon: Float? = null,
        @SerializedName("lat")
        val lat: Float? = null,
    )
}