package com.example.shoppinglistsumin.domain

class DeleteItemFromListUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun deleteItem(shopItem: ShopItem){
        shopListRepository.deleteItem(shopItem)
    }
}