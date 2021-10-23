package com.example.cryptocoinapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.R.layout.fragment_list
import com.example.cryptocoinapp.common.Constants
import com.example.cryptocoinapp.databinding.FragmentListBinding
import com.example.cryptocoinapp.domain.model.Coin
import com.example.cryptocoinapp.presentation.adapter.RecyclerViewAdapter
import com.example.cryptocoinapp.presentation.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*


const val COIN_KEY = "coinKey"

@AndroidEntryPoint
class ListFragment : Fragment(), RecyclerViewAdapter.CoinClickListener {

    private val viewModel: CoinListViewModel by viewModels()

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        binding = DataBindingUtil.inflate(
            inflater,
            fragment_list, container, false
        )

        initRecyclerView()
        initViewModel()

        return binding.root
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            getCoinListViewStateLiveData().observe(
                viewLifecycleOwner,
                {
                    binding.recyclerView.apply {
                        adapter = RecyclerViewAdapter(it.coins, this@ListFragment)
                    }
                }
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }

    override fun onCoinClicked(coin: Coin) {
        val frag = CoinDetailFragment()
        val args = Bundle()
        args.putParcelable(COIN_KEY, coin)
        args.putString(Constants.PARAM_COIN_ID, coin.id.toString())
        args.putString(Constants.PARAM_COIN_NAME, coin.name.lowercase())
        args.putString(Constants.PARAM_COIN_PERIOD, "24h")
        frag.arguments = args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, frag)
            ?.addToBackStack(null)
            ?.commit()
    }
}