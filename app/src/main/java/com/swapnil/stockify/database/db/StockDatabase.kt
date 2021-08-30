package com.swapnil.stockify.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.swapnil.stockify.database.entites.Stock

@Database(entities = [Stock::class], version = 1)
abstract class StockDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): StocksDao

    companion object {
        @Volatile
        private var instance: StockDatabase? = null
        private val LOCK = Any()

        /*
        * Synchronized function so that two threads cannot access this at the same time which can cause memory allocation two times
        * Invoke will be automatically called whenever ShoppingDatabase is instantiated
        *   */
        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StockDatabase::class.java,
                "PortfolioDB.db"
            ).build()
    }
}