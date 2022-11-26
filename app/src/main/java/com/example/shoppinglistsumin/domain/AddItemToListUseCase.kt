package com.example.shoppinglistsumin.domain

class AddItemToListUseCase(private val shopListRepository: ShopListRepository) {

    fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}