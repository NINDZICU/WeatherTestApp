package com.mera.weathertestapp.domain.repository

import com.mera.weathertestapp.domain.entity.CityEntity
import com.mera.weathertestapp.domain.entity.CityWeatherEntity

interface WeatherRepository {

    suspend fun searchCity(search: String): List<CityEntity>
    suspend fun getWeatherForCity(cityId: Int): CityWeatherEntity
}