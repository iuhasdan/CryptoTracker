package com.example.cryptocoinapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.databinding.ActivityMainBinding
import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.presentation.adapter.RecyclerViewAdapter
import com.example.cryptocoinapp.presentation.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint


private lateinit var binding: ActivityMainBinding
const val COIN_KEY = "coinKey"


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.CoinClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.activity = this

        initRecyclerView()
        initViewModel()
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        viewModel.state.observe(this, Observer {
            if (it != null) {
                binding.recyclerView.apply {
                    adapter = RecyclerViewAdapter(it.coins, this@MainActivity)
                }
            }
        })
    }

    override fun onCoinClicked(coin: Coin) {
        val frag = CoinDetailFragment()
        val args = Bundle()
//        args.putParcelable(COIN_KEY, coin)
        args.putString(Constants.PARAM_COIN_ID, coin.id.toString())
        frag.arguments = args
        supportFragmentManager.beginTransaction()
            .add(R.id.container, frag)
            .addToBackStack(null)
            .commit()
    }
}