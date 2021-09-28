package com.mera.weathertestapp.ui.models

import java.time.DayOfWeek

data class DailyWeatherModel(
    val dayOfTheWeek: DayOfWeek,
    val temp: Short,
    val iconType: IconType,
    val hoursWeather: List<HourlyWeatherModel>,
    var isSelected: Boolean = false
)