package com.zvonimirplivelic.shophopper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.zvonimirplivelic.shophopper.R
import com.zvonimirplivelic.shophopper.db.ShopDatabase
import com.zvonimirplivelic.shophopper.repository.ShopRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShopDatabase(this)
        val repository = ShopRepository(database)
        val factory = ShopViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShopListViewModel::class.java)
    }
}