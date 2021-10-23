package com.example.cryptocoinapp.presentation.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.common.utils.Handler
import com.example.cryptocoinapp.databinding.CoinDetailFragmentBinding
import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.presentation.coin_detail.CoinDetailViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailFragment :
    Fragment() {

    private lateinit var binding: CoinDetailFragmentBinding

    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    companion object {
        fun newInstance() = CoinDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.coin_detail_fragment, container, false
        )

        val handler = Handler()
        binding.handler = handler
        setHasOptionsMenu(true)
        val coin = getCoin()

        initViewModel(coin)

        return binding.root
    }

    private fun initializeChart(historicalCoinData: List<Entry>, name: String?) {
        with(binding) {
            lineChart.apply {
                axisRight.textColor = Color.WHITE
                axisLeft.textColor = Color.WHITE
                xAxis.textColor = Color.WHITE
                binding.lineChart.setNoDataTextColor(Color.WHITE)
                binding.lineChart.data = data
                binding.lineChart.animateXY(1000, 1000)
                binding.lineChart.legend.isEnabled = false
                description.text = name
                description.textColor = Color.WHITE
                description.textSize = 16f
                val lineDataSet = LineDataSet(historicalCoinData, "")
                lineDataSet.setDrawCircles(false)
                lineDataSet.lineWidth = 1.5f
                data = LineData(lineDataSet)
                description.textColor = lineDataSet.color
                description.typeface = Typeface.DEFAULT_BOLD
            }
        }
    }

    private fun getCoin(): Coin? {
        val bundle = arguments

        var coin: Coin? = null
        try {
            coin = bundle?.getParcelable(COIN_KEY)
        } catch (e: Exception) {
            Toast.makeText(
                this.context,
                getString(R.string.error_retrieving_coin),
                Toast.LENGTH_SHORT
            ).show()
        }
        return coin
    }

    private fun initViewModel(coin: Coin?) {
        with(coinDetailViewModel) {
            getCoinViewStateLiveData().observe(
                viewLifecycleOwner,
                { coinDetailViewState ->
                    binding.coinDetail = coinDetailViewState
                    binding.coin = coin
                    coin?.let { createChips(it) }
                    setLogoSymbol(binding.coinDetail!!.coin?.logo)
                    setTitle(coin?.name)
                    binding.executePendingBindings()
                }
            ).also {
                getHistoricalDataViewStateLiveData().observe(
                    viewLifecycleOwner,
                    { coinHistoricalViewState ->
                        initializeChart(coinHistoricalViewState.entryList, coin?.name)
                        binding.lineChart.invalidate()
                        binding.executePendingBindings()
                    }
                )
            }
        }
    }

    private fun setTitle(title: String?) {
        activity?.title = title
    }

    private fun createChips(coin: Coin) {
        coin.tags.forEach {
            val chip = Chip(context)
            chip.text = it
            chip.setTextColor(resources.getColor(R.color.primary))
            chip.ellipsize
            chip.setEnsureMinTouchTargetSize(false)
            binding.tagNamesChips.addView(chip)
        }
    }

    private fun setLogoSymbol(logo: String?) {
        Glide.with(this)
            .load(logo)
            .into(binding.logoTv)
    }
}