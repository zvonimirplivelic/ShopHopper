package com.zvonimirplivelic.shophopper.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_description")
    var description: String,
    @ColumnInfo(name = "item_quantity")
    var quantity: Int,
    @ColumnInfo(name = "item_priority")
    var priority: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}