package com.mera.weathertestapp.domain.entity

data class HourlyEntity(
    val windSpeed: Double,
    val temp: Short,
    val weatherType: WeatherType,
    val humidity: Double,
    val hour: Int,
    val rainChance: Double
)
