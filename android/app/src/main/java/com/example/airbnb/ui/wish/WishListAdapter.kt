package com.example.airbnb.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemWishBinding
import com.example.airbnb.domain.model.WishItem

class WishListAdapter () : RecyclerView.Adapter<WishListAdapter.ViewHolder>(){

    private val items= mutableListOf<WishItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemWishBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: WishListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemWishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wishItem: WishItem) {
            binding.wishItem=wishItem
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(items:List<WishItem>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}