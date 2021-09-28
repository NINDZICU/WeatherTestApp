package com.mera.weathertestapp.koin

import com.mera.weathertestapp.data.repository.CityRepositoryImpl
import com.mera.weathertestapp.data.repository.WeatherRepositoryImpl
import com.mera.weathertestapp.domain.repository.CityRepository
import com.mera.weathertestapp.domain.repository.WeatherRepository
import org.koin.dsl.module

fun getRepositoryModule() = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(weatherRestApi = get())
    }

    single<CityRepository> {
        CityRepositoryImpl(settingsPreference = get())
    }
}