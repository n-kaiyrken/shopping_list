package com.example.shoppinglistsumin.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemByIndex(index: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}