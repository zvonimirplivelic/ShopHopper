package com.zvonimirplivelic.shophopper.repository

import com.zvonimirplivelic.shophopper.db.ShopDatabase
import com.zvonimirplivelic.shophopper.db.model.ShopItem

class ShopRepository(
    private val db: ShopDatabase
) {

    suspend fun updateOrInsert(item: ShopItem) = db.getShopDao().updateOrInsert(item)

    suspend fun delete(item: ShopItem) = db.getShopDao().delete(item)

    fun getAllShopItems() = db.getShopDao().getAllShopItems()
}