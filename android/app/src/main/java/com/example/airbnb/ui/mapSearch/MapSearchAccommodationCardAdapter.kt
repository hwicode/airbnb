package com.example.airbnb.ui.mapSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemMapSearchAccommodationCardBinding
import com.example.airbnb.databinding.ItemSearchResultAccommodationBinding
import com.example.airbnb.domain.model.SearchResultAccommodation
import com.example.airbnb.ui.searchResult.SearchResultAdapter

class MapSearchAccommodationCardAdapter(private val itemClick: () -> Unit): ListAdapter<SearchResultAccommodation, MapSearchAccommodationCardAdapter.ViewHolder>(SearchResultAccommodationDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMapSearchAccommodationCardBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MapSearchAccommodationCardAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    class ViewHolder(private val binding: ItemMapSearchAccommodationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: SearchResultAccommodation, itemClick: () -> Unit) {
            binding.accommodation= accommodation
            binding.tvSearchResultHostInfo.isVisible= accommodation.superHost
            binding.root.setOnClickListener {
                itemClick.invoke()
            }

        }
    }

    companion object SearchResultAccommodationDiffUtil : DiffUtil.ItemCallback<SearchResultAccommodation>() {

        override fun areItemsTheSame(oldItem: SearchResultAccommodation, newItem: SearchResultAccommodation): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchResultAccommodation, newItem: SearchResultAccommodation): Boolean {
            return oldItem == newItem
        }
    }
}