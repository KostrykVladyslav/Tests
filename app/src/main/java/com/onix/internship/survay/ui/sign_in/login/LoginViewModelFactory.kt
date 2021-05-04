package com.onix.internship.survay.ui.sign_in.login

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.data.user.UserViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val userViewModel: UserViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    private val context: Context?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userViewModel, viewLifecycleOwner, context) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}