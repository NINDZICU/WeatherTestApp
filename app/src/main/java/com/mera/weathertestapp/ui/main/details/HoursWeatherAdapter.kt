package com.mera.weathertestapp.ui.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.R
import com.mera.weathertestapp.databinding.ItemHourlyWeatherBinding
import com.mera.weathertestapp.databinding.TitleHourlyWeatherBinding
import com.mera.weathertestapp.ui.models.HourlyTableModel
import com.mera.weathertestapp.ui.models.HourlyTitleModel
import com.mera.weathertestapp.ui.models.HourlyWeatherModel
import com.mera.weathertestapp.utils.to12HFormat
import com.mera.weathertestapp.utils.toIntPercent

class HoursWeatherAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TITLE = 0
        private const val HOURLY_ITEM = 1
    }

    private var data = mutableListOf<HourlyTableModel>()

    fun setHourlyWeather(weather: List<HourlyTableModel>) {
        val result = DiffUtil.calculateDiff(HourlyWeatherDiffUtil(data, weather))
        data.clear()
        data.addAll(weather)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
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
        when(val model = data[position]) {
            is HourlyWeatherModel -> (holder as HoursWeatherViewHolder).bind(model)
            is HourlyTitleModel -> (holder as HoursWeatherTitleViewHolder).bind()
        }
    }

    override fun getItemCount(): Int = data.size

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


    private class HourlyWeatherDiffUtil(
        private val old: List<HourlyTableModel>,
        private val new: List<HourlyTableModel>,
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = areContentsTheSame(oldItemPosition, newItemPosition)

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = old[oldItemPosition]
            val newItem = new[newItemPosition]
            return oldItem == newItem
        }

    }
}