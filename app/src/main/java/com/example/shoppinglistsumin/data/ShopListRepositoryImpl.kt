package com.example.shoppinglistsumin.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shoppinglistsumin.domain.ShopItem
import com.example.shoppinglistsumin.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application): ShopListRepository {

    private val shopListDao: ShopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapShopItemtoDbModel(shopItem))
    }

    override suspend fun deleteItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapShopItemtoDbModel(shopItem))
    }

    override suspend fun getItemByIndex(index: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemID = index)
        return mapper.mapDbModelToShopItem(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getShopList()) {
            value = mapper.mapDbModelListToShopList(it)
        }
    }
}