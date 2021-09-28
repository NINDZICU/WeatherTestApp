package com.mera.weathertestapp.ui.mapper

import com.mera.weathertestapp.domain.entity.CityWeatherEntity
import com.mera.weathertestapp.ui.models.CityWeatherModel
import java.time.LocalDate
import java.util.*


class CityWeatherMapper {
    fun map(city: CityWeatherEntity): CityWeatherModel {
        val todayWeather = city.daysWeather.find { it.dayOfTheWeek.toInt() == LocalDate.now().dayOfWeek.value - 1 }
        val currentTemp = todayWeather?.hoursWeather?.find { it.hour == Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }

        return city.run {
            CityWeatherModel(
                cityName,
                cityCode,
                imageUrl,
                temperature = currentTemp?.temp ?: 0
            )
        }
    }
}
