package com.swapnil.stockify.database.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.swapnil.stockify.database.entites.Stock

@Dao
interface StocksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(stock: Stock)

    @Delete
    suspend fun delete(stock: Stock)

    @Query("SELECT * FROM portfolio_stocks")
    fun getAllShoppingItems():LiveData<List<Stock>>
}