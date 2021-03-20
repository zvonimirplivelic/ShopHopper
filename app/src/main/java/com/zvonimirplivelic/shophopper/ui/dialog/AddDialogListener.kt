package com.zvonimirplivelic.shophopper.ui.dialog

import com.zvonimirplivelic.shophopper.db.model.ShopItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShopItem)
}