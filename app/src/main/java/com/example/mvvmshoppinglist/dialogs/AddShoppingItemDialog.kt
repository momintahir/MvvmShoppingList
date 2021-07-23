package com.example.mvvmshoppinglist.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.mvvmshoppinglist.R
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.interfaces.AddDialogInterface
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(
    context: Context,
    var addDialogListener: AddDialogInterface
) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "please enter all information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item =ShoppingItem(name,amount.toInt())
            addDialogListener.addButton(item)
            dismiss()
        }
        tvCancel.setOnClickListener{
            cancel()
        }
    }
}