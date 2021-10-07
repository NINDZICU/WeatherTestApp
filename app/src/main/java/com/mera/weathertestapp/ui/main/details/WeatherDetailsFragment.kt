package com.mera.weathertestapp.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mera.weathertestapp.R
import com.mera.weathertestapp.databinding.FragmentWeatherBinding
import com.mera.weathertestapp.ui.base.BaseFragment
import com.mera.weathertestapp.utils.toDateTimeString
import org.koin.android.ext.android.inject
import java.time.DayOfWeek
import java.util.*


class WeatherDetailsFragment : BaseFragment<FragmentWeatherBinding, WeatherDetailsViewModel>() {

    override val viewModel: WeatherDetailsViewModel by inject<WeatherDetailsViewModelImpl>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding =
        FragmentWeatherBinding::inflate

    private val selectDayListener = object : DaysWeatherAdapter.DaysListener {
        override fun onSelectDay(day: DayOfWeek) {
            viewModel.onSetDay(day)
        }
    }

    private val daysAdapter = DaysWeatherAdapter(selectDayListener)
    private val hoursAdapter = HoursWeatherAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dailyList.adapter = daysAdapter
            dailyList.layoutManager = GridLayoutManager(context, 7)
            hourlyList.adapter = hoursAdapter
            hourlyList.layoutManager = LinearLayoutManager(context)

            viewModel.isProgress().observe {
                if (it) progress.root.visibility = View.VISIBLE
                else progress.root.visibility = View.GONE
            }

            viewModel.cityInfo().observe {
                setTitleImage(it.imageUrl)
                cityName.text = "${it.cityName}, ${it.cityCode}"
                cityDate.text = Calendar.getInstance().time.toDateTimeString()
                cityTemp.text = getString(R.string.degrees, it.temperature)
            }

            viewModel.dailyWeather().observe {
                daysAdapter.submitList(it.map { weather -> weather.copy() })
            }

            viewModel.hourlyWeather().observe {
                hoursAdapter.submitList(it)
            }

            appbar.toolbar.setNavigationOnClickListener {
                getNavController().navigate(R.id.action_weatherFragment_to_searchFragment)
            }
        }
    }


    private fun setTitleImage(imageUrl: String?) {
        Glide
            .with(this)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.back_placeholder)
                    .centerCrop()
            )
            .into(binding.cityImage)
    }
}