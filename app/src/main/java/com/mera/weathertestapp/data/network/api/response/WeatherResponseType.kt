package com.mera.weathertestapp.data.network.api.response

import com.google.gson.annotations.SerializedName

enum class WeatherResponseType(val value: String) {
    @SerializedName("sunny")
    SUNNY("sunny"),
    @SerializedName("snowSleet")
    SNOW_SLEET("snowSleet"),
    @SerializedName("heavyRain")
    HEAVY_RAIN("heavyRain"),
    @SerializedName("cloudly")
    CLOUDLY("cloudly"),
    @SerializedName("lightRain")
    LIGHT_RAIN("lightRain"),
    @SerializedName("partlyCloudy")
    PARTLY_CLOUDY("partlyCloudy")
}