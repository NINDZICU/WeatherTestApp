package com.mera.weathertestapp

import android.app.Application
import com.mera.weathertestapp.koin.getDataModule
import com.mera.weathertestapp.koin.getInteractorModule
import com.mera.weathertestapp.koin.getRepositoryModule
import com.mera.weathertestapp.koin.getViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@WeatherApplication)
            modules(getModules())
        }
    }

    private fun getModules() = listOf(
        getViewModelModule(),
        getInteractorModule(),
        getRepositoryModule(),
        getDataModule()
    )
}