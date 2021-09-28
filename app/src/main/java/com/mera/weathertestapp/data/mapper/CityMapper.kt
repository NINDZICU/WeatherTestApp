package com.mera.weathertestapp.data.mapper

import com.mera.weathertestapp.data.network.api.response.CityItem
import com.mera.weathertestapp.domain.entity.CityEntity

object CityMapper {
    fun map(city: CityItem): CityEntity {
        return city.run {
            CityEntity(
                id = id,
                name = name
            )
        }
    }
}