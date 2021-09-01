package com.swapnil.stockify

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.swapnil.stockify.database.StockViewModel
import com.swapnil.stockify.database.StockViewModelFactory
import com.swapnil.stockify.databinding.ActivityStockBinding

lateinit var binding:ActivityStockBinding
class StockActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val  stockViewModel:StockViewModel by viewModels {
            StockViewModelFactory((application as MyApplication).repository)
        }
        initViews(binding)
    }
    private fun initViews(binding: ActivityStockBinding) {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
            ?: return
        setupActionBarWithNavController(navHostFragment.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }

}