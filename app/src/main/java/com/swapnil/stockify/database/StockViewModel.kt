package com.swapnil.stockify.database

import androidx.lifecycle.ViewModel
import com.swapnil.stockify.database.entites.Stock
import com.swapnil.stockify.database.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel(private val repository: ShoppingRepository) : ViewModel() {
    fun upsert(stock: Stock) {
        /*Dispatchers.Main will launch this on Main thread
        * */

        CoroutineScope(Dispatchers.Main)
            .launch {
                repository.upsert(stock)
            }
    }

    fun delete(stock: Stock) {
        CoroutineScope(Dispatchers.Main)
            .launch {
                repository.delete(stock)
            }
    }

    fun getAllStocks() = repository.getAllShoppingItems()
}