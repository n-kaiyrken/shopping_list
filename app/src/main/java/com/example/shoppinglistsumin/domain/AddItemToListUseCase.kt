package com.example.shoppinglistsumin.domain

class AddItemToListUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}