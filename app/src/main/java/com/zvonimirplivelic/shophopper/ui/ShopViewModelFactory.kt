package com.zvonimirplivelic.shophopper.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zvonimirplivelic.shophopper.repository.ShopRepository

class ShopViewModelFactory(
    private val repository: ShopRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopViewModel(repository) as T
    }
}