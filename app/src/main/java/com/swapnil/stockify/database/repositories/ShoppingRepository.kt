package com.swapnil.stockify.database.repositories

import com.swapnil.stockify.database.db.StockDatabase
import com.swapnil.stockify.database.entites.Stock

class ShoppingRepository(private val db: StockDatabase) {

    suspend fun upsert(stock: Stock) = db.getShoppingDao().upsert(stock)

    suspend fun delete(stock: Stock) = db.getShoppingDao().delete(stock)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}