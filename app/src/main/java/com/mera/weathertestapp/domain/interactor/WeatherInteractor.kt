package com.mera.weathertestapp.domain.interactor

import com.mera.weathertestapp.domain.entity.CityEntity
import com.mera.weathertestapp.domain.entity.CityWeatherEntity
import com.mera.weathertestapp.domain.repository.WeatherRepository

class WeatherInteractor(private val weatherRepository: WeatherRepository) {

    suspend fun searchCity(search: String): List<CityEntity> {
        return weatherRepository.searchCity(search)
    }

    suspend fun getWeatherForCity(cityId: Int): CityWeatherEntity {
        return weatherRepository.getWeatherForCity(cityId)
    }
}