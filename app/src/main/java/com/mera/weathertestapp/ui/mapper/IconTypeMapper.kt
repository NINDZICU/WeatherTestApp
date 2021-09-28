package com.mera.weathertestapp.ui.mapper

import com.mera.weathertestapp.domain.entity.WeatherType
import com.mera.weathertestapp.ui.models.IconType

class IconTypeMapper {
    fun map(type: WeatherType): IconType {
        return when(type) {
            WeatherType.SUNNY -> IconType.SUNNY
            WeatherType.SNOW_SLEET -> IconType.SNOW_SLEET
            WeatherType.HEAVY_RAIN -> IconType.HEAVY_RAIN
            WeatherType.CLOUDLY -> IconType.CLOUDLY
            WeatherType.LIGHT_RAIN -> IconType.LIGHT_RAIN
            WeatherType.PARTLY_CLOUDY -> IconType.PARTLY_CLOUDY
            else -> IconType.NONE
        }
    }
}