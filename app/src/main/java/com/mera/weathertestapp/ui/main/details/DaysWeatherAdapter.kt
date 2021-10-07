package com.mera.weathertestapp.ui.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.R
import com.mera.weathertestapp.databinding.ItemDailyWeatherBinding
import com.mera.weathertestapp.ui.models.DailyWeatherModel
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

class DaysWeatherAdapter(
    private val daysListener: DaysListener
    ) : ListAdapter<DailyWeatherModel, DaysWeatherAdapter.DaysWeatherViewHolder>(DailyWeatherDiffUtil()) {

    interface DaysListener {
        fun onSelectDay(day: DayOfWeek)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeatherViewHolder {
        val binding = ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaysWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DaysWeatherViewHolder(private val binding: ItemDailyWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DailyWeatherModel) = binding.apply {
            dailyTitle.text = model.dayOfTheWeek.getDisplayName(TextStyle.SHORT, Locale.US)
            dailyTemperature.text = itemView.context.getString(R.string.degrees, model.temp)
            dailyImage.setImageResource(model.iconType.iconRes)

            itemView.setOnClickListener {
                daysListener.onSelectDay(model.dayOfTheWeek)
            }


            val selectedColor =
                if(model.isSelected) ContextCompat.getColor(itemView.context, R.color.white)
                else ContextCompat.getColor(itemView.context, R.color.dark_blue)

            dailyTitle.setTextColor(selectedColor)
            dailyTemperature.setTextColor(selectedColor)
            dailyImage.setColorFilter(selectedColor)
        }
    }

    private class DailyWeatherDiffUtil: DiffUtil.ItemCallback<DailyWeatherModel>() {

        override fun areItemsTheSame(oldItem: DailyWeatherModel, newItem: DailyWeatherModel): Boolean =
            oldItem.dayOfTheWeek == newItem.dayOfTheWeek && oldItem.iconType == newItem.iconType &&
                oldItem.temp == newItem.temp && oldItem.isSelected == newItem.isSelected

        override fun areContentsTheSame(oldItem: DailyWeatherModel, newItem: DailyWeatherModel): Boolean =
            oldItem == newItem

    }

}