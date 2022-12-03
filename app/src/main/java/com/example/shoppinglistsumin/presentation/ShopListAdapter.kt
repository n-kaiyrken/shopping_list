package com.example.shoppinglistsumin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppinglistsumin.databinding.ItemDisabledBinding
import com.example.shoppinglistsumin.databinding.ItemEnabledBinding
import com.example.shoppinglistsumin.domain.ShopItem


class ShopListAdapter() : androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(
    ShopItemDiffCallback()
) {

    var onShopItemLongClickListener: ((shopItem: ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((shopItem: ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        return when (viewType) {
            VIEW_TYPE_ENABLED -> ShopItemViewHolder.ShopListViewHolderEnabled(
                ItemEnabledBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_DISABLED -> ShopItemViewHolder.ShopListViewHolderDisabled(
                ItemDisabledBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        when (holder) {
            is ShopItemViewHolder.ShopListViewHolderEnabled -> holder.bind(shopItem)
            is ShopItemViewHolder.ShopListViewHolderDisabled -> holder.bind(shopItem)
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 101
        const val VIEW_TYPE_DISABLED = 100
        const val MAX_POOL_SIZE = 15
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }
}