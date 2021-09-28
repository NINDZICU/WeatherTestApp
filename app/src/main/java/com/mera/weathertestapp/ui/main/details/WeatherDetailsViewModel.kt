package com.mera.weathertestapp.ui.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mera.weathertestapp.domain.interactor.CityInteractor
import com.mera.weathertestapp.domain.interactor.WeatherInteractor
import com.mera.weathertestapp.ui.base.BaseViewModel
import com.mera.weathertestapp.ui.base.BaseViewModelImpl
import com.mera.weathertestapp.ui.mapper.CityWeatherMapper
import com.mera.weathertestapp.ui.mapper.DailyWeatherMapper
import com.mera.weathertestapp.ui.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek

interface WeatherDetailsViewModel: BaseViewModel {
    fun isProgress(): LiveData<Boolean>
    fun cityInfo(): LiveData<CityWeatherModel>
    fun dailyWeather(): LiveData<List<DailyWeatherModel>>
    fun hourlyWeather(): LiveData<List<HourlyTableModel>>

    fun onSetDay(day: DayOfWeek)
}

class WeatherDetailsViewModelImpl(
    private val weatherInteractor: WeatherInteractor,
    private val cityInteractor: CityInteractor
): BaseViewModelImpl(), WeatherDetailsViewModel {

    private val isProgressLiveData = MutableLiveData(false)
    private val cityInfoLiveData = MutableLiveData<CityWeatherModel>()
    private val dailyLiveData = MutableLiveData<List<DailyWeatherModel>>()
    private val hourlyWeatherLiveData = MutableLiveData<List<HourlyTableModel>>()

    private var selectedDay: DayOfWeek = DayOfWeek.MONDAY

    override fun isProgress(): LiveData<Boolean> = isProgressLiveData
    override fun cityInfo(): LiveData<CityWeatherModel> = cityInfoLiveData
    override fun dailyWeather(): LiveData<List<DailyWeatherModel>> = dailyLiveData
    override fun hourlyWeather(): LiveData<List<HourlyTableModel>> = hourlyWeatherLiveData

    init {
        viewModelScope.launch {
            cityInteractor.getCityIdFlow().collect { id ->
                updateWeather(id)
            }
        }
    }

    private suspend fun updateWeather(cityId: Int) {
        isProgressLiveData.value = true
        try {
            val weather = withContext(Dispatchers.IO) {
                weatherInteractor.getWeatherForCity(cityId)
            }

            cityInfoLiveData.value = CityWeatherMapper().map(weather)
            dailyLiveData.value = weather.daysWeather
                .sortedBy { it.dayOfTheWeek }
                .map(DailyWeatherMapper()::map)

            onSetDay(DayOfWeek.MONDAY)
        } catch (e: Throwable) {
            postError(e)
        } finally {
            isProgressLiveData.value = false
        }
    }

    override fun onSetDay(day: DayOfWeek) {
        dailyLiveData.value = dailyLiveData.value?.apply {
            this.first { model -> model.dayOfTheWeek == selectedDay }.isSelected = false
            this.first { model -> model.dayOfTheWeek == day }.isSelected = true
        }
        selectedDay = day

        setHourlyWeather(dailyLiveData.value?.get(selectedDay.value - 1)?.hoursWeather.orEmpty())
    }

    private fun setHourlyWeather(weather: List<HourlyWeatherModel>) {
        hourlyWeatherLiveData.value = listOf(
            HourlyTitleModel, *weather.toTypedArray()
        )
    }

}