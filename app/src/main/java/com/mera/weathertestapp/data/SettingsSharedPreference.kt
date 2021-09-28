package com.mera.weathertestapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsSharedPreference constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val CITY_ID = intPreferencesKey("screen_orientation")
    }

    val cityIdFlow: Flow<Int?> = dataStore.data
        .map { preferences -> preferences[CITY_ID] }


    suspend fun saveCityId(cityId: Int) {
        dataStore.edit { settings ->
            settings[CITY_ID] = cityId
        }
    }
}