package com.example.shoppinglistsumin.presentation

import androidx.databinding.BindingAdapter
import com.example.shoppinglistsumin.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, error: Boolean){
    if (error) {
        textInputLayout.error = textInputLayout.context.getString(R.string.error_invalid_name)
    } else {
        textInputLayout.error = null
    }
}

@BindingAdapter ("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, error: Boolean) {
    if (error) {
        textInputLayout.error = textInputLayout.context.getString(R.string.error_invalid_count)
    } else {
        textInputLayout.error = null
    }
}