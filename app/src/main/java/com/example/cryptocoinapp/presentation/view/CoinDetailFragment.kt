package com.example.cryptocoinapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.databinding.CoinDetailFragmentBinding
import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.presentation.coin_detail.CoinDetailState
import com.example.cryptocoinapp.presentation.coin_detail.CoinDetailViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.coin_detail_fragment.*


@AndroidEntryPoint
class CoinDetailFragment :
    Fragment() {

    private lateinit var binding: CoinDetailFragmentBinding

    companion object {
        fun newInstance() = CoinDetailFragment()
    }

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
        val coin = getCoin()
        initializeChart(coin)

        initViewModel(coin)

        return binding.root
    }

    private fun setLogoSymbol(resource: CoinDetailState) {
        Glide.with(this)
            .load(resource.coin?.logo)
            .into(binding.logoTv)
    }

    private fun initializeChart(coin: Coin?) {
        val xvalue = ArrayList<String>()
        xvalue.add("1.00AM")
        xvalue.add("3.00AM")
        xvalue.add("5.00AM")
        xvalue.add("7.00AM")
        xvalue.add("9.00AM")
        val dataVals: ArrayList<Entry> = ArrayList()
        if (coin != null) {
            dataVals.add(Entry(1f, coin.percentChange1h.toFloat()))
            dataVals.add(Entry(2f, coin.percentChange24h.toFloat()))
            dataVals.add(Entry(3f, coin.percentChange30d.toFloat()))
            dataVals.add(Entry(4f, coin.percentChange60d.toFloat()))
            dataVals.add(Entry(5f, coin.percentChange90d.toFloat()))
        }
        val lineDataSet = LineDataSet(dataVals, "First")
        lineDataSet.color = resources.getColor(R.color.green)

        val data = LineData(lineDataSet)

        binding.lineChart.data = data
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.lineChart.animateXY(3000, 3000)
        binding.lineChart.isDragEnabled = true
        binding.lineChart.setScaleEnabled(true)
    }

    private fun getCoin(): Coin? {
        val bundle = arguments

        var coin: Coin? = null
        try {
            coin = bundle?.getParcelable<Coin>(COIN_KEY)
        } catch (e: Exception) {
            Toast.makeText(this.context, "Error retrieving Coin!", Toast.LENGTH_SHORT).show()
        }
        return coin
    }

    private fun initViewModel(coin: Coin?) {
        viewModel = ViewModelProvider(this).get(CoinDetailViewModel::class.java)
        binding.lifecycleOwner = this

        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.coinNameTv.text = it.coin?.name
                binding.priceTv.text = coin?.price.toString()
                binding.rankTv.text = coin?.cmcRank.toString()
                binding.percentChangeTv.text = coin?.percentChange24h.toString()
                binding.descriptionTv.text = it.coin?.description
                it.coin?.tagNames?.forEach {
                    val chip = Chip(context)
                    chip.text = it
                    chip.setTextColor(resources.getColor(R.color.primary))
                    chip.ellipsize
                    chip.setEnsureMinTouchTargetSize(false)
                    binding.tagNamesChips.addView(chip)
                }
                setLogoSymbol(it)
            }
        })
    }
}