package com.example.shoppinglistsumin.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglistsumin.domain.ShopItem
import com.example.shoppinglistsumin.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({ t, t2 -> t.id.compareTo(t2.id) })

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000){
            val item = ShopItem("Name-$i", i, Random.nextBoolean())
            addItem(item)
        }
    }

    override fun addItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateLD()
    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateLD()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getItemByIndex(shopItem.id)
        shopList.remove(oldItem)
        addItem(shopItem)
    }

    override fun getItemByIndex(index: Int): ShopItem {
        return shopList.find {
            it.id == index
        } ?: throw RuntimeException("Error: Item with index $index not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    fun updateLD(){
        shopListLiveData.value = shopList.toList()
    }

}