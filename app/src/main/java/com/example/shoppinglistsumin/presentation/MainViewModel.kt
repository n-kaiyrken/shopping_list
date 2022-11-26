package com.example.shoppinglistsumin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistsumin.data.ShopListRepositoryImpl
import com.example.shoppinglistsumin.domain.*

class MainViewModel: ViewModel() {

    private val shopListRepository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)
    private val deleteItemFromListUseCase = DeleteItemFromListUseCase(shopListRepository)
    private val editItemUseCase = EditItemUseCase(shopListRepository)

    val shopListLiveData = getShopListUseCase.getShopList()

    fun deleteItemFromList(item: ShopItem){
        deleteItemFromListUseCase.deleteItem(item)
    }

    fun changeEnabledStateItem(item: ShopItem){
        val newitem = item.copy(enabled = !item.enabled)
        editItemUseCase.editShopItem(newitem)
    }

}