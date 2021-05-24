package com.onix.internship.survay.binding_adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R
import com.onix.internship.survay.arch.error_states.ErrorStates

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorStates: ErrorStates){
    error = when (errorStates){
        ErrorStates.NONE -> ""
        ErrorStates.EMPTY_FIELD -> context.getString(R.string.field_is_empty)
        ErrorStates.INCORRECT_DATA -> context.getString(R.string.incorrect_data)
        ErrorStates.LOGIN_HAS_USED -> context.getString(R.string.login_used)
        ErrorStates.PASSWORD_NOT_THE_SAME -> context.getString(R.string.incorrect_password_confirm)
    }
}