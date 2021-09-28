package com.mera.weathertestapp.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mera.weathertestapp.databinding.ItemCityNameBinding
import com.mera.weathertestapp.domain.entity.CityEntity

class SearchResultsAdapter(private val resultListener: SearchResultsListener): RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    interface SearchResultsListener {
        fun onSelectResult(city: CityEntity)
    }

    private var data = mutableListOf<CityEntity>()

    fun setCities(cities: List<CityEntity>) {
        data.clear()
        data.addAll(cities)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val binding = ItemCityNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class SearchResultsViewHolder(private val binding: ItemCityNameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityEntity) = binding.apply {
            name.text = city.name

            itemView.setOnClickListener {
                resultListener.onSelectResult(city)
            }
        }
    }
}