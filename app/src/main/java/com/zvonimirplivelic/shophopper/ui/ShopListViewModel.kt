package com.zvonimirplivelic.shophopper.ui

import androidx.lifecycle.ViewModel
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import com.zvonimirplivelic.shophopper.repository.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListViewModel(
    private val repository: ShopRepository
) : ViewModel() {
    fun updateOrInsertItem(item: ShopItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.updateOrInsert(item)
    }

    fun delete(item: ShopItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }


    fun getAllShopItems() = repository.getAllShopItems()
}