package com.mera.weathertestapp.domain.entity

data class DailyEntity(
    val dayOfTheWeek: Short,
    val lowTemp: Short,
    val highTemp: Short,
    val weatherType: WeatherType,
    val hoursWeather: List<HourlyEntity>
)
