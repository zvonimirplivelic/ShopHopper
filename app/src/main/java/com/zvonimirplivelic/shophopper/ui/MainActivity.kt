package com.zvonimirplivelic.shophopper.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zvonimirplivelic.shophopper.R
import com.zvonimirplivelic.shophopper.db.ShopDatabase
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import com.zvonimirplivelic.shophopper.other.ShopItemAdapter
import com.zvonimirplivelic.shophopper.repository.ShopRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShopDatabase(this)
        val repository = ShopRepository(database)
        val factory = ShopViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShopListViewModel::class.java)

        val adapter = ShopItemAdapter(applicationContext, listOf(), viewModel)

        rvShopItemList.layoutManager = LinearLayoutManager(this)
        rvShopItemList.adapter = adapter

        viewModel.getAllShopItems().observe(this, {
            adapter.shopItems = it
            adapter.notifyDataSetChanged()

            if (it.isEmpty()) {
                emptyMessage.visibility = View.VISIBLE
                ivCart.visibility = View.VISIBLE
            } else {
                emptyMessage.visibility = View.GONE
                ivCart.visibility = View.GONE
            }
        })

        fabAddItem.setOnClickListener {
            AddShopItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShopItem) {
                    viewModel.updateOrInsertItem(item)
                }
            }).show()
        }
    }
}