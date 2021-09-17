package com.example.cryptocoinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoinapp.R
import com.example.cryptocoinapp.adapter.RecyclerViewAdapter
import com.example.cryptocoinapp.databinding.ActivityMainBinding
import com.example.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding: ActivityMainBinding
private lateinit var adapter: RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }


    private fun initRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCoinsListObserver().observe(this, {
            if (it != null) {
                binding.recyclerView.apply {
                    adapter = RecyclerViewAdapter(it.data)
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.error_retrieving_data),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.makeApiCall()
    }
}