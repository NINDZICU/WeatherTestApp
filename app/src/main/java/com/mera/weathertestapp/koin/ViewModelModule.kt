package com.mera.weathertestapp.koin

import androidx.navigation.NavController
import com.mera.weathertestapp.ui.main.MainViewModelImpl
import com.mera.weathertestapp.ui.main.details.WeatherDetailsViewModelImpl
import com.mera.weathertestapp.ui.main.search.SearchViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModelModule() = module {

    viewModel { MainViewModelImpl() }

    viewModel {
        WeatherDetailsViewModelImpl(
            weatherInteractor = get(),
            cityInteractor = get()
        )
    }

    viewModel { (navController: NavController) ->
        SearchViewModelImpl(
            navController = navController,
            weatherInteractor = get(),
            cityInteractor = get()
        )
    }
}