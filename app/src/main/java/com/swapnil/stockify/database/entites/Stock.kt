package com.swapnil.stockify.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio_stocks")
data class Stock(
    var shareName: String,
    var companySector: String,
    var sharePrize: Float,
    var shareQty: Float,
    var note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}