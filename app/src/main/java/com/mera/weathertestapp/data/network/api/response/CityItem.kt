package com.mera.weathertestapp.data.network.api.response

import com.google.gson.annotations.SerializedName

data class CityItem(
    @SerializedName("geonameid")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("admin1 code")
    val code: String,
    @SerializedName("imageURLs")
    val imageURLs: ImageURLs?,
)

data class ImageURLs (
    val androidImageURLs: AndroidImageURLs,
)

data class AndroidImageURLs (
    val xhdpiImageURL: String?,
    val hdpiImageURL: String?,
    val mdpiImageURL: String?
)