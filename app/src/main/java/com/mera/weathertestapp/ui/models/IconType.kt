package com.mera.weathertestapp.ui.models

import com.mera.weathertestapp.R

enum class IconType(val iconRes: Int) {
    SUNNY(R.drawable.ic_sunny),
    SNOW_SLEET(R.drawable.ic_snow_sleet),
    HEAVY_RAIN(R.drawable.ic_heavy_rain),
    CLOUDLY(R.drawable.ic_cloudy),
    LIGHT_RAIN(R.drawable.ic_light_rain),
    PARTLY_CLOUDY(R.drawable.ic_partly_cloudy),
    NONE(R.drawable.ic_empty)
}