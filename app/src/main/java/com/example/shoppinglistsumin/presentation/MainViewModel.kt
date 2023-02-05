package com.example.shoppinglistsumin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistsumin.data.ShopListRepositoryImpl
import com.example.shoppinglistsumin.domain.DeleteItemFromListUseCase
import com.example.shoppinglistsumin.domain.EditItemUseCase
import com.example.shoppinglistsumin.domain.GetShopListUseCase
import com.example.shoppinglistsumin.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val shopListRepository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)
    private val deleteItemFromListUseCase = DeleteItemFromListUseCase(shopListRepository)
    private val editItemUseCase = EditItemUseCase(shopListRepository)

    val shopListLiveData = getShopListUseCase.getShopList()

    fun deleteItemFromList(item: ShopItem) {
        viewModelScope.launch {
            deleteItemFromListUseCase.deleteItem(item)
        }
    }

    fun changeEnabledStateItem(item: ShopItem) {
        viewModelScope.launch {
            val newitem = item.copy(enabled = !item.enabled)
            editItemUseCase.editShopItem(newitem)
        }
    }
}