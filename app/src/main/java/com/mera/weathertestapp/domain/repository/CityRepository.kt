package com.mera.weathertestapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun saveCityId(cityId: Int)
    suspend fun getCityIdFlow(): Flow<Int?>
}