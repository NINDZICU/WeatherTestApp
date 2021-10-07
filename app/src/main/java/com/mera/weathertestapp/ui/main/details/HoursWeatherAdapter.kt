package com.mera.weathertestapp.ui.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.R
import com.mera.weathertestapp.databinding.ItemHourlyWeatherBinding
import com.mera.weathertestapp.databinding.TitleHourlyWeatherBinding
import com.mera.weathertestapp.ui.models.HourlyTableModel
import com.mera.weathertestapp.ui.models.HourlyTitleModel
import com.mera.weathertestapp.ui.models.HourlyWeatherModel
import com.mera.weathertestapp.utils.to12HFormat
import com.mera.weathertestapp.utils.toIntPercent

class HoursWeatherAdapter: ListAdapter<HourlyTableModel, RecyclerView.ViewHolder>(HourlyWeatherDiffUtil()) {

    companion object {
        private const val TITLE = 0
        private const val HOURLY_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            HourlyTitleModel -> TITLE
            is HourlyWeatherModel -> HOURLY_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TITLE -> HoursWeatherTitleViewHolder(TitleHourlyWeatherBinding.inflate(inflater, parent, false))
            HOURLY_ITEM -> HoursWeatherViewHolder(ItemHourlyWeatherBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Not valid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val model = getItem(position)) {
            is HourlyWeatherModel -> (holder as HoursWeatherViewHolder).bind(model)
            is HourlyTitleModel -> (holder as HoursWeatherTitleViewHolder).bind()
        }
    }

    inner class HoursWeatherViewHolder(private val binding: ItemHourlyWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HourlyWeatherModel) = binding.apply {
            icon.setImageResource(model.iconType.iconRes)
            time.text = model.hour.to12HFormat()
            temp.text = itemView.context.getString(R.string.degrees, model.temp)
            chanceRain.text = itemView.context.getString(R.string.percent_value, model.rainChance.toIntPercent())
            wind.text = model.windSpeed.toString()
            humidity.text = itemView.context.getString(R.string.percent_value, model.humidity.toIntPercent())

        }
    }

    inner class HoursWeatherTitleViewHolder(binding: TitleHourlyWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }


    private class HourlyWeatherDiffUtil: DiffUtil.ItemCallback<HourlyTableModel>() {

        override fun areItemsTheSame(oldItem: HourlyTableModel, newItem: HourlyTableModel) = oldItem == newItem

        override fun areContentsTheSame(oldItem: HourlyTableModel, newItem: HourlyTableModel) = oldItem == newItem
    }
}