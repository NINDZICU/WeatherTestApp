package com.mera.weathertestapp.domain.interactor

import com.mera.weathertestapp.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityInteractor(private val cityRepository: CityRepository) {
    companion object {
        private const val DEFAULT_CITY_ID = 4053050
    }

    suspend fun saveCityId(cityId: Int) {
        cityRepository.saveCityId(cityId)
    }


    suspend fun getCityIdFlow(): Flow<Int> {
        return cityRepository.getCityIdFlow().map {
            it ?: DEFAULT_CITY_ID
        }
    }
}