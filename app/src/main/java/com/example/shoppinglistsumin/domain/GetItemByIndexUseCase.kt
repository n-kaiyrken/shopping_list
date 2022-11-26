package com.example.shoppinglistsumin.domain

class GetItemByIndexUseCase(private val shopListRepository: ShopListRepository) {
    fun getItemByIndex(index: Int): ShopItem{
        return shopListRepository.getItemByIndex(index)
    }
}