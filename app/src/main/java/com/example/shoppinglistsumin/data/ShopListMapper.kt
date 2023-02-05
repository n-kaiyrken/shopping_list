package com.example.shoppinglistsumin.data

import androidx.lifecycle.Transformations.map
import com.example.shoppinglistsumin.domain.ShopItem

class ShopListMapper {

    fun mapShopItemtoDbModel(shopItem: ShopItem): ShopItemDBModel {
        return ShopItemDBModel(
            shopItem.id,
            shopItem.name,
            shopItem.count,
            shopItem.enabled
        )
    }

    fun mapDbModelToShopItem(shopItemDBModel: ShopItemDBModel) :ShopItem{
        return ShopItem(
            shopItemDBModel.name,
            shopItemDBModel.count,
            shopItemDBModel.enabled,
            shopItemDBModel.id
        )
    }

    fun mapDbModelListToShopList(shopItemDBModelList: List<ShopItemDBModel>): List<ShopItem> {
        return shopItemDBModelList.map {
            mapDbModelToShopItem(it)
        }
    }
}