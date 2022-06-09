package com.example.airbnb.ui.searchResult


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemSearchResultAccommodationBinding
import com.example.airbnb.databinding.ItemSearchResultProgressbarBinding
import com.example.airbnb.domain.model.SearchResult
import com.example.airbnb.domain.model.SearchResultAccommodation
import com.example.airbnb.domain.model.SearchResultProgressBar

const val VIEW_TYPE_LOADING = 0
const val VIEW_TYPE_ITEM = 1

class SearchResultAdapter(private val itemClick: (Int) -> Unit, private val conditionClick:(Int)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items= mutableListOf<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_LOADING-> LoadingViewHolder(ItemSearchResultProgressbarBinding.inflate(inflater,parent,false))
            else-> ItemViewHolder(ItemSearchResultAccommodationBinding.inflate(inflater,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LoadingViewHolder->{
                val item= items[position] as SearchResultProgressBar
                holder.bind(item)
            }
            is ItemViewHolder->{
                val item= items[position] as SearchResultAccommodation
                holder.bind(item, itemClick, conditionClick)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SearchResultProgressBar -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class ItemViewHolder(private val binding: ItemSearchResultAccommodationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: SearchResultAccommodation, itemClick: (accommodationID: Int) -> Unit, conditionClick: (accommodationID: Int) -> Unit) {
            binding.accommodation= accommodation
            binding.tvSearchResultHostInfo.isVisible= accommodation.superHost

            binding.iBtnWish.setOnClickListener {
                conditionClick.invoke(accommodation.accommodationID)
            }
            binding.root.setOnClickListener {
                itemClick.invoke(accommodation.accommodationID)
            }

        }
    }
    inner class LoadingViewHolder(private val binding:ItemSearchResultProgressbarBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(loadingBar: SearchResultProgressBar){
            binding.searchResultProgressBar.isVisible= loadingBar.isLoading
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(items:List<SearchResult>){
        this.items.addAll(items)
        this.items.add(SearchResultProgressBar(true))
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }

}