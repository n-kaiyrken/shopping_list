package com.example.shoppinglistsumin.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglistsumin.domain.ShopItem

@Entity (tableName = "shop_items")
data class ShopItemDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
)