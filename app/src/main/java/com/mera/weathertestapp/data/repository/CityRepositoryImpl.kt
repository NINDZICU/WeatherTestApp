package com.mera.weathertestapp.data.repository

import com.mera.weathertestapp.data.SettingsSharedPreference
import com.mera.weathertestapp.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class CityRepositoryImpl(
    private val settingsPreference: SettingsSharedPreference
): CityRepository {

    override suspend fun saveCityId(cityId: Int) {
        settingsPreference.saveCityId(cityId)
    }

    override suspend fun getCityIdFlow(): Flow<Int?> {
        return settingsPreference.cityIdFlow
    }
}