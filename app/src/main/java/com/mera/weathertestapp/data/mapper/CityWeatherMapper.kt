package com.mera.weathertestapp.data.mapper

import com.mera.weathertestapp.data.network.api.response.CityDetailItem
import com.mera.weathertestapp.domain.entity.CityWeatherEntity

object CityWeatherMapper {

    fun map(cityDetail: CityDetailItem): CityWeatherEntity {
        return cityDetail.run {
            CityWeatherEntity(
                cityName = city.name,
                cityCode = city.code,
                imageUrl = city.imageURLs?.androidImageURLs?.xhdpiImageURL,
                daysWeather = weather.days.map(DailyWeatherMapper::map)
            )
        }
    }
}