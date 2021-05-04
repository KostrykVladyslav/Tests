package com.onix.internship.survay.binding_adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R

@BindingAdapter("errorMassage")
fun TextInputLayout.errorMessage(errorState: Boolean){
    error = if (errorState){
        context.getString(R.string.field_is_empty)
    } else ""
}