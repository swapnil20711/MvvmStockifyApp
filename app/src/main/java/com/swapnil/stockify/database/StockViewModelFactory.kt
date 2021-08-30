package com.swapnil.stockify.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swapnil.stockify.database.repositories.ShoppingRepository

class StockViewModelFactory(private val repository: ShoppingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}