package com.mera.weathertestapp.koin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mera.weathertestapp.BuildConfig
import com.mera.weathertestapp.data.SettingsSharedPreference
import com.mera.weathertestapp.data.network.api.WeatherApiWrapper
import com.mera.weathertestapp.data.network.api.WeatherRestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun getDataModule() = module {
    single {
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    single<WeatherRestApi> {
        WeatherApiWrapper(
            WeatherRestApi.getInstance(
                baseUrl = BuildConfig.WEATHER_URL,
                okHttpClient = get()
            )
        )
    }

    single {
        SettingsSharedPreference(
            androidApplication().dataStore
        )
    }
}