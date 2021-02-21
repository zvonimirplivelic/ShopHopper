package com.zvonimirplivelic.shophopper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        val viewModel = ViewModelProviders.of(this, factory).get(ShopListViewModel::class.java)

        val adapter = ShopItemAdapter(listOf(), viewModel)

        rvShopItemList.layoutManager = LinearLayoutManager(this)
        rvShopItemList.adapter = adapter

        viewModel.getAllShopItems().observe(this, {
            adapter.shopItems = it
            adapter.notifyDataSetChanged()

            if (it.isEmpty()) {
                emptyMessage.visibility = View.VISIBLE
                ivCart.visibility = View.VISIBLE
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