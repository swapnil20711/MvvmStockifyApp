package com.swapnil.stockify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.swapnil.stockify.databinding.FragmentDashboardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
lateinit var fragmentDashboardBinding: FragmentDashboardBinding
class DashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDashboardBinding= FragmentDashboardBinding.inflate(layoutInflater,container,false)
        bindButtons()
        // Inflate the layout for this fragment
        return fragmentDashboardBinding.root
    }

    private fun bindButtons() {
        fragmentDashboardBinding.btnAddStock.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addStockFragment)
        }
    }
}