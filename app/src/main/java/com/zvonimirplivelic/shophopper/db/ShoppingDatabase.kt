package com.zvonimirplivelic.shophopper.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zvonimirplivelic.shophopper.db.model.ShopItem

@Database(
    entities = [ShopItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun getShopDao(): ShopDao

    companion object {
        @Volatile
        private var INSTANCE: ShopDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShopDatabase::class.java,
                "shop_db"
            ).build()
    }
}