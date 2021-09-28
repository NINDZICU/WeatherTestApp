package com.mera.weathertestapp.data.mapper

import com.mera.weathertestapp.data.network.api.response.DayItem
import com.mera.weathertestapp.domain.entity.DailyEntity

object DailyWeatherMapper {

    fun map(dayItem: DayItem): DailyEntity {
        return dayItem.run {
            DailyEntity(
                dayOfTheWeek = dayOfTheWeek,
                lowTemp = low,
                highTemp = high,
                weatherType = WeatherTypeMapper.map(weatherType),
                hoursWeather = hourlyWeather.map(HourlyWeatherMapper::map)
            )
        }
    }
}