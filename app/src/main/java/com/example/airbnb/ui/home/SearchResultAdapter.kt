package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemSearchResultDestinationBinding
import com.example.airbnb.domain.model.SearchResultDestination

class SearchResultAdapter : ListAdapter<SearchResultDestination, SearchResultAdapter.ViewHolder>(SearchResultDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemSearchResultDestinationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchResultAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemSearchResultDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(destination: SearchResultDestination) {
            binding.destination = destination
        }
    }

    companion object SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResultDestination>() {

        override fun areItemsTheSame(oldItem: SearchResultDestination, newItem: SearchResultDestination): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchResultDestination, newItem: SearchResultDestination): Boolean {
            return oldItem == newItem
        }
    }
}