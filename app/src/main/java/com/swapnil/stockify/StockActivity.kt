package com.swapnil.stockify

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.swapnil.stockify.database.StockViewModel
import com.swapnil.stockify.database.StockViewModelFactory
import com.swapnil.stockify.databinding.ActivityStockBinding

lateinit var binding:ActivityStockBinding
class StockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val  stockViewModel:StockViewModel by viewModels {
            StockViewModelFactory((application as MyApplication).repository)
        }
    }

}