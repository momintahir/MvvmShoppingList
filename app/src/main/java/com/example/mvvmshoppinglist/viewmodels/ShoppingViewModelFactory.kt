package com.example.mvvmshoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmshoppinglist.data.repositories.ShoppingRepositories

class ShoppingViewModelFactory(
    private val repository: ShoppingRepositories
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T

    }

}