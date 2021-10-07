package com.mera.weathertestapp.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mera.weathertestapp.domain.entity.CityEntity
import com.mera.weathertestapp.domain.interactor.CityInteractor
import com.mera.weathertestapp.domain.interactor.WeatherInteractor
import com.mera.weathertestapp.ui.base.BaseViewModel
import com.mera.weathertestapp.ui.base.BaseViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface SearchViewModel : BaseViewModel {
    fun isProgress(): LiveData<Boolean>
    fun isEmptyData(): LiveData<Boolean>
    fun results(): LiveData<List<CityEntity>>

    fun onSearch(search: String)
    fun onSelectResult(city: CityEntity)
}

class SearchViewModelImpl(
    private val navController: NavController,
    private val weatherInteractor: WeatherInteractor,
    private val cityInteractor: CityInteractor
) : BaseViewModelImpl(), SearchViewModel {

    private val isProgressLiveData = MutableLiveData(false)
    private val isEmptyLiveData = MutableLiveData(false)
    private val resultsLiveData = MutableLiveData<List<CityEntity>>()

    override fun isProgress(): LiveData<Boolean> = isProgressLiveData
    override fun isEmptyData(): LiveData<Boolean> = isEmptyLiveData
    override fun results(): LiveData<List<CityEntity>> = resultsLiveData

    override fun onSearch(search: String) {
        if(search.isNotBlank()) {
            viewModelScope.launch {
                try {
                    resultsLiveData.value = emptyList()
                    isProgressLiveData.value = true
                    isEmptyLiveData.value = false

                    val cities = withContext(Dispatchers.IO) {
                        weatherInteractor.searchCity(search)
                    }

                    if(cities.isNotEmpty()) {
                        resultsLiveData.value = cities
                    } else {
                        isEmptyLiveData.value = true
                    }

                } catch (e: Throwable) {
                    postError(e)
                } finally {
                    isProgressLiveData.value = false
                }
            }
        }
    }

    override fun onSelectResult(city: CityEntity) {
        viewModelScope.launch {
            cityInteractor.saveCityId(city.id)
            navController.popBackStack()
        }
    }
}