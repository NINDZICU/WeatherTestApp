package com.mera.weathertestapp.ui.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.R
import com.mera.weathertestapp.databinding.ItemDailyWeatherBinding
import com.mera.weathertestapp.ui.models.DailyWeatherModel
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

class DaysWeatherAdapter(private val daysListener: DaysListener) : RecyclerView.Adapter<DaysWeatherAdapter.DaysWeatherViewHolder>() {

    interface DaysListener {
        fun onSelectDay(day: DayOfWeek)
    }

    private var data = mutableListOf<DailyWeatherModel>()

    fun setDailyWeather(weather: List<DailyWeatherModel>) {
        val result = DiffUtil.calculateDiff(DailyWeatherDiffUtil(data, weather))
        data.clear()
        data.addAll(weather.map { it.copy() })
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeatherViewHolder {
        val binding = ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaysWeatherViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

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

    private class DailyWeatherDiffUtil(
        private val old: List<DailyWeatherModel>,
        private val new: List<DailyWeatherModel>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = old[oldItemPosition]
            val newItem = new[newItemPosition]

            return oldItem.dayOfTheWeek == newItem.dayOfTheWeek && oldItem.iconType == newItem.iconType &&
                    oldItem.temp == newItem.temp && oldItem.isSelected == newItem.isSelected
        }

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

    }

}