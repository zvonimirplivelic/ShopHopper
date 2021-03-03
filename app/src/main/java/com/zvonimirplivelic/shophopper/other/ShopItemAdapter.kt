package com.zvonimirplivelic.shophopper.other

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.shophopper.R
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import com.zvonimirplivelic.shophopper.ui.ShopListViewModel
import kotlinx.android.synthetic.main.shop_item.view.*

class ShopItemAdapter(
    private val context: Context,
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
        holder.itemView.tvItemName.text = context.getString(R.string.name_item_string, currentItem.name)
        holder.itemView.tvItemDescription.text = context.getString(R.string.description_item_string, currentItem.description)
        holder.itemView.tvItemPriority.text = context.getString(R.string.priority_item_string, currentItem.priority)
        holder.itemView.tvItemQuantity.text = context.getString(R.string.quantity_item_string, currentItem.quantity.toString())

        holder.itemView.ivDelete.setOnClickListener {

            val builder: AlertDialog.Builder = holder.itemView.ivDelete.context.let {
                AlertDialog.Builder(it)
            }.apply {

                setTitle(context.getString(R.string.delete_item_dialog))
                setMessage(context.getString(R.string.delete_dialog_message, currentItem.name))
                setIcon(R.drawable.ic_delete)

                setPositiveButton(R.string.yes) { dialog, id ->
                    viewModel.delete(currentItem)
                    dialog.dismiss()
                }

                setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
            }

            builder.create()?.show()
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