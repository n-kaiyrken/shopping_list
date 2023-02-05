package com.example.shoppinglistsumin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglistsumin.domain.ShopItem

@Dao
interface ShopListDao {
    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDBModel: ShopItemDBModel)

    @Query("DELETE FROM shop_items WHERE id =:shopItemID")
    suspend fun deleteShopItem(shopItemID: Int)

    @Query("SELECT * FROM shop_items WHERE id=:shopItemID LIMIT 1")
    suspend fun getShopItem(shopItemID: Int): ShopItemDBModel
}