package com.example.cryptocoinapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.databinding.CoinDetailFragmentBinding
import com.example.cryptocoinapp.presentation.coin_detail.CoinDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment :
    Fragment()/*, OnChartGestureListener*//*, OnChartValueSelectedListener*/ {

    private lateinit var binding: CoinDetailFragmentBinding

    companion object {
        fun newInstance() = CoinDetailFragment()
    }

    //    val chart: LineChart
    private lateinit var viewModel: CoinDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.coin_detail_fragment, container, false
        )

        setHasOptionsMenu(true)
        savedInstanceState?.getInt(Constants.PARAM_COIN_ID)


        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this).get(CoinDetailViewModel::class.java)
        binding.lifecycleOwner = this

        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.coinNameTv.text = it.coin?.name
//                binding.priceTv.text = it.coin?.name
//                binding.rankTv.text = it.coin?.name
//                binding.coinNameTv.text = it.coin?.name
//                binding.coinNameTv.text = it.coin?.name
//                viewModel.state.value
//                it.coin?.name

            }
        })
    }
}