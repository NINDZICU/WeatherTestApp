package com.mera.weathertestapp.data.mapper

import com.mera.weathertestapp.data.network.api.response.WeatherResponseType
import com.mera.weathertestapp.domain.entity.WeatherType

object WeatherTypeMapper {
    fun map(type: WeatherResponseType?): WeatherType {
        return when(type) {
            WeatherResponseType.SUNNY -> WeatherType.SUNNY
            WeatherResponseType.SNOW_SLEET -> WeatherType.SNOW_SLEET
            WeatherResponseType.HEAVY_RAIN -> WeatherType.HEAVY_RAIN
            WeatherResponseType.CLOUDLY -> WeatherType.CLOUDLY
            WeatherResponseType.LIGHT_RAIN -> WeatherType.LIGHT_RAIN
            WeatherResponseType.PARTLY_CLOUDY -> WeatherType.PARTLY_CLOUDY
            else -> WeatherType.NONE
        }
    }
}