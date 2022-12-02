package com.example.shoppinglistsumin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglistsumin.data.ShopListRepositoryImpl
import com.example.shoppinglistsumin.domain.*

class ShopItemViewModel : ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    val shopListRepositoryImpl = ShopListRepositoryImpl

    val getItemByIndexUseCase = GetItemByIndexUseCase(shopListRepositoryImpl)
    val addItemToListUseCase = AddItemToListUseCase(shopListRepositoryImpl)
    val editItemUseCase = EditItemUseCase(shopListRepositoryImpl)

    fun addNewItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addItemToListUseCase.addItem(shopItem)
            finishWork()
        }
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

    fun getShopItem(id: Int) {
        val item = getItemByIndexUseCase.getItemByIndex(id)
        _shopItem.value = item
    }

    fun editItem(id: Int, inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            shopItem.value?.let {
                val newItem = it.copy(name = name, count = count)
                editItemUseCase.editShopItem(newItem)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
        }
        if (count <= 0) {
            result = false
            _errorInputCount.value = true
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
}