package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemSearchResultDestinationBinding
import com.example.airbnb.domain.model.SearchResultDestination

class SearchAdapter(private val itemClick: () -> Unit)  : ListAdapter<SearchResultDestination, SearchAdapter.ViewHolder>(SearchResultDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemSearchResultDestinationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position),itemClick)
    }

    class ViewHolder(private val binding: ItemSearchResultDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(destination: SearchResultDestination, itemClick: () -> Unit) {
            binding.destination = destination
            binding.clSearchResult.setOnClickListener {
                // 검색된 결과중 하나 클릭시 정보입력 페이지로 이동
                itemClick.invoke()
            }

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