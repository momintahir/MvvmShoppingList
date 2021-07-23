package com.example.mvvmshoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppinglist.adapters.ShoppingItemAdapter
import com.example.mvvmshoppinglist.data.db.ShoppingDatabase
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.data.repositories.ShoppingRepositories
import com.example.mvvmshoppinglist.dialogs.AddShoppingItemDialog
import com.example.mvvmshoppinglist.interfaces.AddDialogInterface
import com.example.mvvmshoppinglist.viewmodels.ShoppingViewModel
import com.example.mvvmshoppinglist.viewmodels.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepositories(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItem.layoutManager = LinearLayoutManager(this)
        rvShoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.item = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogInterface {
                override fun addButton(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }


}