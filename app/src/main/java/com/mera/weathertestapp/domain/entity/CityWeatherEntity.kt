package com.mera.weathertestapp.domain.entity

data class CityWeatherEntity(
    val cityName: String,
    val cityCode: String,
    val imageUrl: String?,
    val daysWeather: List<DailyEntity>
)
