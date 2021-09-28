package com.mera.weathertestapp.data.network.api.response

data class HourlyWeatherItem(
    val windSpeed: Double,
    val temperature: Short,
    val weatherType: WeatherResponseType?,
    val humidity: Double,
    val hour: Int,
    val rainChance: Double
)
