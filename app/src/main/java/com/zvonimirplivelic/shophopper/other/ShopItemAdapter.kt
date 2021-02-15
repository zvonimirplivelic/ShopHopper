package com.zvonimirplivelic.shophopper.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.shophopper.R
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import com.zvonimirplivelic.shophopper.ui.ShopListViewModel
import kotlinx.android.synthetic.main.shop_item.view.*

class ShopItemAdapter(
    var shopItems: List<ShopItem>,
    private val viewModel: ShopListViewModel
) : RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemAdapter.ShopItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemAdapter.ShopItemViewHolder, position: Int) {

        val currentItem = shopItems[position]
        holder.itemView.tvItemName.text = currentItem.name
        holder.itemView.tvItemDescription.text = currentItem.description
        holder.itemView.tvItemPriority.text = currentItem.priority.toString()
        holder.itemView.tvItemQuantity.text = currentItem.quantity.toString()

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentItem.quantity++
            viewModel.updateOrInsertItem(currentItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.quantity > 0) {
                currentItem.quantity--
                viewModel.updateOrInsertItem(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = shopItems.size

    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}