package com.zvonimirplivelic.shophopper.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatDialog
import com.zvonimirplivelic.shophopper.R
import com.zvonimirplivelic.shophopper.db.model.ShopItem
import kotlinx.android.synthetic.main.dialog_add_shop_item.*

class AddShopItemDialog(
    context: Context,
    var addDialogListener: AddDialogListener
) : AppCompatDialog(context),
    AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shop_item)
        setTitle("Add shop item")

        var spinner: Spinner = spinnerPriority

        ArrayAdapter.createFromResource(
            context,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

        ibConfirm.setOnClickListener {
            val name = etItemName.text.toString()
            val quantity = etItemQuantity.text.toString().toInt()
            val description = etItemDescription.text.toString()


            if (name.isEmpty() ||
                description.isEmpty() ||
                quantity.equals("")
            ) {
                Toast.makeText(context, "Please fill required information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val spinnerValue: String = spinner.selectedItem.toString()

            val item = ShopItem(name, description, quantity, spinnerValue)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        ibCancel.setOnClickListener {
            cancel()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}