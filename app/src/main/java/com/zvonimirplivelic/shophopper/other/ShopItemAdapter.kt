package com.zvonimirplivelic.shophopper.other

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import com.zvonimirplivelic.shophopper.ui.ShopListViewModel

class ShoppingItemAdapter(
    var items: List<ShopItem>,
    private val viewModel: ShopListViewModel
): RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder>(){

    inner class ShopItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}