package com.mera.weathertestapp.ui.mapper

import com.mera.weathertestapp.domain.entity.HourlyEntity
import com.mera.weathertestapp.ui.models.HourlyWeatherModel

class HourlyWeatherMapper {
    fun map(weather: HourlyEntity): HourlyWeatherModel {
        return weather.run {
            HourlyWeatherModel(
                windSpeed,
                temp,
                iconType = IconTypeMapper().map(weatherType),
                humidity,
                hour,
                rainChance
            )
        }
    }
}