package com.example.shoppinglistsumin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistsumin.R
import com.example.shoppinglistsumin.databinding.ActivityShopitemBinding
import com.example.shoppinglistsumin.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopitemBinding
    private var shopItemId = ShopItem.UNDEFINED_ID
    private var screenMode = MODE_UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopitemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        //viewModel = ViewModelProvider(this).get(ShopItemViewModel::class.java)
        launchRightMode()
        // observeViewModel()
        //addTextChangedListeners()
    }

    //    private fun observeViewModel() {
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//        viewModel.errorInputName.observe(this) {
//            if (it) {
//                binding.tilName.error = getString(R.string.error_invalid_name)
//            } else {
//                binding.tilName.error = null
//            }
//        }
//        viewModel.errorInputCount.observe(this) {
//            if (it) {
//                binding.tilCount.error = getString(R.string.error_invalid_count)
//            } else {
//                binding.tilCount.error = null
//            }
//        }
//    }
//
//    private fun addTextChangedListeners() {
//        binding.etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
//        binding.etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
//    }
//
    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode: $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_shopItem_container_view, fragment)
            .commit()
    }

    //
//    private fun launchAddMode() {
//        binding.saveButton.setOnClickListener {
//            val name = binding.etName.text?.toString()
//            val count = binding.etCount.text?.toString()
//            viewModel.addNewItem(name, count)
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            binding.etName.setText(it.name)
//            binding.etCount.setText(it.count.toString())
//        }
//        binding.saveButton.setOnClickListener {
//            val name = binding.etName.text?.toString()
//            val count = binding.etCount.text?.toString()
//            viewModel.editItem(name, count)
//        }
//    }
//
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent!")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item is absent!")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}
