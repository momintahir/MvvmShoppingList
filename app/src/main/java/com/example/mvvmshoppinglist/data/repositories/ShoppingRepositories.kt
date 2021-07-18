package com.example.mvvmshoppinglist.data.repositories

import com.example.mvvmshoppinglist.data.db.ShoppingDatabase
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem

class ShoppingRepositories(private val db: ShoppingDatabase) {

    suspend fun upsert(item: ShoppingItem) {
        db.getShoppingDao().upsert(item)
    }

    //    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()

}