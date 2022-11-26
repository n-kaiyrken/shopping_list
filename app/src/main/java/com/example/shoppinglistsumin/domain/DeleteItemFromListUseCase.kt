package com.example.shoppinglistsumin.domain

class DeleteItemFromListUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteItem(shopItem: ShopItem){
        shopListRepository.deleteItem(shopItem)
    }
}