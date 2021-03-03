package com.zvonimirplivelic.shophopper.ui

import android.content.Context
import android.os.Bundle
import android.view.View
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
            val description = etItemDescription.text.toString()
            val spinnerValue: String = spinner.selectedItem.toString()
            var quantity = etItemQuantity.text.toString()

            if (name.isEmpty() ||
                description.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    "Please fill required information",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            } else if (quantity.isEmpty()) {
                Toast.makeText(context, "Quantity value isn't set. Default value is 0", Toast.LENGTH_SHORT).show()
                quantity = "0"
            }

            val item = ShopItem(name, description, quantity.toInt(), spinnerValue)

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

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}