package com.example.shoppinglistsumin.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun addItem(shopItem: ShopItem)

    suspend fun deleteItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getItemByIndex(index: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}