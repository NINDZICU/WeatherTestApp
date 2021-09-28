package com.mera.weathertestapp.data.repository

import com.mera.weathertestapp.data.mapper.CityMapper
import com.mera.weathertestapp.data.mapper.CityWeatherMapper
import com.mera.weathertestapp.data.network.api.WeatherRestApi
import com.mera.weathertestapp.domain.entity.CityEntity
import com.mera.weathertestapp.domain.entity.CityWeatherEntity
import com.mera.weathertestapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherRestApi: WeatherRestApi
): WeatherRepository {

    override suspend fun searchCity(search: String): List<CityEntity> {
        val city = weatherRestApi.getCities(search)
        return city.cities.map(CityMapper::map)
    }

    override suspend fun getWeatherForCity(cityId: Int): CityWeatherEntity {
        val cityDetailsItem = weatherRestApi.getWeatherForCity(cityId)
        return CityWeatherMapper.map(cityDetailsItem)
    }
}