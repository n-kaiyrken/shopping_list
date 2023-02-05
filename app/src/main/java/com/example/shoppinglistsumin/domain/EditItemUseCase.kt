package com.example.shoppinglistsumin.domain

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}