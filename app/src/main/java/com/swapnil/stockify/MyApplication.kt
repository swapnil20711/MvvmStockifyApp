package com.swapnil.stockify

import android.app.Application
import com.swapnil.stockify.database.db.StockDatabase
import com.swapnil.stockify.database.repositories.ShoppingRepository

class MyApplication: Application() {
    val database by lazy {
        StockDatabase.invoke(this)
    }
    val repository by lazy {
        ShoppingRepository(database)
    }
}