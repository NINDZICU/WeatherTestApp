package com.mera.weathertestapp.ui.models

sealed class HourlyTableModel

data class HourlyWeatherModel(
    val windSpeed: Double,
    val temp: Short,
    val iconType: IconType,
    val humidity: Double,
    val hour: Int,
    val rainChance: Double
): HourlyTableModel()

object HourlyTitleModel: HourlyTableModel()