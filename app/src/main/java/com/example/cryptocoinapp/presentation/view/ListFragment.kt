package com.example.cryptocoinapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var viewModel: CoinListViewModel
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        var decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CoinListViewModel::class.java)
        viewModel.state.observe(this.viewLifecycleOwner, Observer {
            if (it != null) {
                binding.recyclerView.apply {
                    adapter = RecyclerViewAdapter(it.coins, this@ListFragment)
                }
            }
        })
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
        frag.arguments = args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, frag)
            ?.addToBackStack(null)
            ?.commit()
    }
}