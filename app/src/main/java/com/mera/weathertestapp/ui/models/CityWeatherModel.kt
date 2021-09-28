package com.mera.weathertestapp.ui.models

data class CityWeatherModel(
    val cityName: String,
    val cityCode: String,
    val imageUrl: String?,
    val temperature: Short
)