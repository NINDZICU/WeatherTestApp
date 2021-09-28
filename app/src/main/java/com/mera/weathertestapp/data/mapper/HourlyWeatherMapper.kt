package com.mera.weathertestapp.data.mapper

import com.mera.weathertestapp.data.network.api.response.HourlyWeatherItem
import com.mera.weathertestapp.domain.entity.HourlyEntity

object HourlyWeatherMapper {

    fun map(hourItem: HourlyWeatherItem): HourlyEntity {
        return hourItem.run {
            HourlyEntity(
                windSpeed,
                temperature,
                WeatherTypeMapper.map(weatherType),
                humidity,
                hour,
                rainChance
            )
        }
    }
}