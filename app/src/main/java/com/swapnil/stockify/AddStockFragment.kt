package com.swapnil.stockify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.swapnil.stockify.database.StockViewModel
import com.swapnil.stockify.database.StockViewModelFactory
import com.swapnil.stockify.database.entites.Stock
import com.swapnil.stockify.databinding.FragmentAddStockBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddStockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
lateinit var addStockBinding: FragmentAddStockBinding

class AddStockFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val stockViewModel: StockViewModel by viewModels {
            StockViewModelFactory((activity!!.application as MyApplication).repository)
        }
        addStockBinding = FragmentAddStockBinding.inflate(layoutInflater, container, false)
        initViews()
        setUpButtons(stockViewModel)
        return addStockBinding.root
    }

    private fun initViews() {
        val companySectors =
            ArrayAdapter<String>(
                requireContext(),
                R.layout.item_autocomplete_layout,
                Constants.companiesSector
            )
        addStockBinding.addStockLayout.etCompanyType.setAdapter(companySectors)
        addStockBinding.addStockLayout.etCompanyType.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                if (addStockBinding.addStockLayout.etCompanyType.error?.isNotEmpty() == true) {
                    addStockBinding.addStockLayout.etCompanyType.error = null
                }
            }
    }

    private fun setUpButtons(stockViewModel: StockViewModel) {
        with(addStockBinding.addStockLayout) {
            saveStock.setOnClickListener {
                val (name, sector, prize, qty, note) = getStock()
                when {
                    name.isEmpty() -> {
                        etStockName.error = "Name cannot be empty"
                    }
                    sector.isEmpty() -> {
                        etCompanyType.error = "Company sector can't be empty"
                        return@setOnClickListener
                    }
                    prize.equals(0f) -> {
                        etStockPrize.error = "Stock Prize can't be empty"
                        return@setOnClickListener
                    }
                    qty.equals(0f) -> {
                        etStockQty.error = "Stock Qty can't be empty"
                        return@setOnClickListener
                    }
                    note.isEmpty() -> {
                        etNote.error = "Note can't be empty"
                        return@setOnClickListener
                    }
                    else -> {
                        stockViewModel.upsert(getStock())
                        findNavController().navigate(R.id.action_addStockFragment_to_dashboardFragment)
                        Toast.makeText(
                            context,
                            "Stock added to portfolio successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun getStock(): Stock {
        addStockBinding.addStockLayout.let {
            var sharePrize = 0f
            var shareQty = 0f

            if (!it.etStockPrize.text.isNullOrEmpty()) {
                sharePrize = it.etStockPrize.text.toString().toFloat()
            }
            if (!it.etStockQty.text.isNullOrEmpty()) {
                shareQty = it.etStockQty.text.toString().toFloat()
            }
            return Stock(
                it.etStockName.text.toString(), it.etCompanyType.text.toString(),
                sharePrize,
                shareQty,
                it.etNote.text.toString()
            )
        }
    }
}