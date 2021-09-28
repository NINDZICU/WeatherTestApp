package com.mera.weathertestapp.data.network.api

import com.mera.weathertestapp.data.network.api.response.CityDetailItem
import com.mera.weathertestapp.data.network.api.response.CityPageItem
import retrofit2.HttpException

class WeatherApiWrapper(
    private val weatherApi: WeatherRestApi
) : WeatherRestApi by weatherApi {

    private suspend fun <T> execute(request: suspend () -> T): T {
        return try {
            request()
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun getCities(search: String): CityPageItem {
        return execute { weatherApi.getCities(search) }
    }

    override suspend fun getWeatherForCity(cityId: Int): CityDetailItem {
        return execute { weatherApi.getWeatherForCity(cityId) }
    }
}