package com.example.shoppinglistsumin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistsumin.R
import com.example.shoppinglistsumin.databinding.ActivityMainBinding
import com.example.shoppinglistsumin.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopListLiveData.observe(this) {
            showlist(it)
        }
    }

    private fun showlist(list: List<ShopItem>){
        binding.llList.removeAllViews()
        for (shopItem in list){
            val resId = if (shopItem.enabled == true) {
                R.layout.item_enabled
            } else {
                R.layout.item_disabled
            }
            val view = LayoutInflater.from(this).inflate(resId, binding.llList, false)
            val tv_name = view.findViewById<TextView>(R.id.textView_name)
            val tv_count = view.findViewById<TextView>(R.id.textView_count)
            tv_name.text = shopItem.name
            tv_count.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnabledStateItem(shopItem)
                true
            }
            binding.llList.addView(view)
        }
    }
}