package com.mera.weathertestapp.data.network.api.response

data class WeatherItem(
    val id: Long,
    val days: List<DayItem>
)
