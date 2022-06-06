package com.example.airbnb.ui.searchResult


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemSearchResultAccommodationBinding
import com.example.airbnb.domain.model.SearchResultAccommodation

class SearchResultAdapter(private val itemClick: () -> Unit, private val conditionClick:()->Unit) :
    ListAdapter<SearchResultAccommodation, SearchResultAdapter.ViewHolder>(SearchResultAccommodationDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemSearchResultAccommodationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchResultAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick, conditionClick)
    }

    class ViewHolder(private val binding: ItemSearchResultAccommodationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: SearchResultAccommodation, itemClick: () -> Unit, conditionClick: () -> Unit) {
            binding.accommodation= accommodation
            binding.tvSearchResultHostInfo.isVisible= accommodation.superHost
            binding.iBtnWish.setOnClickListener {
                conditionClick.invoke()
            }
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