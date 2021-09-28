package com.mera.weathertestapp.data.network.api

import com.google.gson.GsonBuilder
import com.mera.weathertestapp.data.network.api.response.CityDetailItem
import com.mera.weathertestapp.data.network.api.response.CityPageItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRestApi {
    companion object {

        private fun getGson() = GsonBuilder()
            .create()

        fun getInstance(baseUrl: String, okHttpClient: OkHttpClient): WeatherRestApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .client(okHttpClient)
            .build()
            .create(WeatherRestApi::class.java)
    }

    @GET("/cities")
    suspend fun getCities(@Query("search") search: String): CityPageItem

    @GET("/cities/{cityId}")
    suspend fun getWeatherForCity(@Path("cityId") cityId: Int): CityDetailItem
}