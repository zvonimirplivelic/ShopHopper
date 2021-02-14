package com.zvonimirplivelic.shophopper.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zvonimirplivelic.shophopper.db.model.ShopItem

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrInsert(item: ShopItem)

    @Delete
    suspend fun delete(item: ShopItem)

    @Query("SELECT * FROM shop_items")
    fun getAllShopItems(): LiveData<LiveData<ShopItem>>
}