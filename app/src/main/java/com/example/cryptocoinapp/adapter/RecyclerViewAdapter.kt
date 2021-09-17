package com.example.cryptocoinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.data.Currencies
import com.example.cryptocoinapp.data.Data
import com.example.cryptocoinapp.databinding.LayoutCoinListItemBinding

class RecyclerViewAdapter(private val data: List<Currencies>) :
    RecyclerView.Adapter<RecyclerViewAdapter.CurrenciesViewHolder>() {

//    private var items: List<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {

        val binding =
            LayoutCoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrenciesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }


    inner class CurrenciesViewHolder constructor(val binding: LayoutCoinListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: Currencies) {
            binding.listItem = items
        }
    }


}
