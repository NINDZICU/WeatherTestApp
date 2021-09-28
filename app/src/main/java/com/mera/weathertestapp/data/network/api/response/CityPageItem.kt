package com.mera.weathertestapp.data.network.api.response

import com.google.gson.annotations.SerializedName

data class CityPageItem(
    @SerializedName("cities")
    val cities: List<CityItem>,
    @SerializedName("totalCitiesFound")
    val totalCitiesFound: Long,
    @SerializedName("startIndex")
    val startIndex: Long,
)