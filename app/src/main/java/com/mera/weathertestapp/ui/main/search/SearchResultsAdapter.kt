package com.mera.weathertestapp.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.databinding.ItemCityNameBinding
import com.mera.weathertestapp.domain.entity.CityEntity

class SearchResultsAdapter(
    private val resultListener: SearchResultsListener
    ): ListAdapter<CityEntity, SearchResultsAdapter.SearchResultsViewHolder>(CityDiffUtil()) {

    interface SearchResultsListener {
        fun onSelectResult(city: CityEntity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val binding = ItemCityNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class SearchResultsViewHolder(private val binding: ItemCityNameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityEntity) = binding.apply {
            name.text = city.name

            itemView.setOnClickListener {
                resultListener.onSelectResult(city)
            }
        }
    }

    private class CityDiffUtil : DiffUtil.ItemCallback<CityEntity>() {
        override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity) =
            oldItem.id == newItem.id && oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity) = oldItem == newItem
    }
}