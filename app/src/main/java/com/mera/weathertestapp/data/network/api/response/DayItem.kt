package com.mera.weathertestapp.data.network.api.response

data class DayItem(
    val dayOfTheWeek: Short,
    val low: Short,
    val high: Short,
    val weatherType: WeatherResponseType?,
    val hourlyWeather: List<HourlyWeatherItem>
)
