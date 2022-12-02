package com.example.shoppinglistsumin.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.shoppinglistsumin.databinding.ItemDisabledBinding
import com.example.shoppinglistsumin.databinding.ItemEnabledBinding
import com.example.shoppinglistsumin.domain.ShopItem

sealed class ShopItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class ShopListViewHolderEnabled(
        private val binding: ItemEnabledBinding
    ) : ShopItemViewHolder(binding){
        fun bind(shopItem: ShopItem){
            binding.textViewName.text = shopItem.name
            binding.textViewCount.text = shopItem.count.toString()
        }
    }

    class ShopListViewHolderDisabled(
        private val binding: ItemDisabledBinding
    ) : ShopItemViewHolder(binding){
        fun bind(shopItem: ShopItem){
            binding.textViewName.text = shopItem.name
            binding.textViewCount.text = shopItem.count.toString()
        }
    }
}
