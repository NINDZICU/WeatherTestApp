package com.mera.weathertestapp.koin

import com.mera.weathertestapp.domain.interactor.CityInteractor
import com.mera.weathertestapp.domain.interactor.WeatherInteractor
import org.koin.dsl.module

fun getInteractorModule() = module {
    single {
        WeatherInteractor(weatherRepository = get())
    }

    single {
        CityInteractor(cityRepository = get())
    }
}