package com.onix.internship.survay.binding_adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorState: Boolean){
    error = if (errorState){
        context.getString(R.string.field_is_empty)
    } else ""
}

@BindingAdapter("incorrectPasswordConfirm")
fun TextInputLayout.incorrectPasswordConfirm(errorState: Boolean){
    error = if (errorState){
        context.getString(R.string.incorrect_password_confirm)
    } else ""
}

@BindingAdapter("incorrectData")
fun TextInputLayout.incorrectData(errorState: Boolean){
    error = if (errorState){
        context.getString(R.string.incorrect_data)
    } else ""
}

@BindingAdapter("loginUsed")
fun TextInputLayout.loginUsed(errorState: Boolean){
    error = if (errorState){
        context.getString(R.string.login_used)
    } else ""
}