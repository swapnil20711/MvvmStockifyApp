package com.swapnil.stockify.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio_stocks")
data class Stock(
    var title: String,
    var companySector: String,
    var sharePrize: Int,
    var shareQty: Int,
    var note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}