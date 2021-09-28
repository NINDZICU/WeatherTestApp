package com.mera.weathertestapp.ui.mapper

import com.mera.weathertestapp.domain.entity.DailyEntity
import com.mera.weathertestapp.ui.models.DailyWeatherModel
import java.time.DayOfWeek

class DailyWeatherMapper {
    fun map(weather: DailyEntity): DailyWeatherModel {
        return DailyWeatherModel(
            dayOfTheWeek = DayOfWeek.of(weather.dayOfTheWeek.toInt() + 1),
            temp = ((weather.lowTemp + weather.highTemp) / 2).toShort(),
            iconType = IconTypeMapper().map(weather.weatherType),
            hoursWeather = weather.hoursWeather.map(HourlyWeatherMapper()::map)
        )
    }
}