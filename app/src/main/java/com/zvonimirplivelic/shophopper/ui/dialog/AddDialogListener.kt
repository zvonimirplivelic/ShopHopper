package com.zvonimirplivelic.shophopper.ui

import com.zvonimirplivelic.shophopper.db.model.ShopItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShopItem)
}