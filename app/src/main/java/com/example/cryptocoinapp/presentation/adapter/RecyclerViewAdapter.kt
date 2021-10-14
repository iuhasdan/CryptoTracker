package com.example.cryptocoinapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinapp.databinding.LayoutCoinListItemBinding
import com.example.cryptocoinapp.domain.model.Coin
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor(
    private val data: List<Coin>,
    private val listener : CoinClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.CurrenciesViewHolder>() {

//    private var items: List<Data> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {

        val binding =
            LayoutCoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrenciesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(data[position], listener)

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CurrenciesViewHolder constructor(val binding: LayoutCoinListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: Coin, listener: CoinClickListener) {
            binding.listItem = items
            binding.coinItemClick = listener
        }
    }

    interface CoinClickListener {
        fun onCoinClicked(coin: Coin)
    }
}
