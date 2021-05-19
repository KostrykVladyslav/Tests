package com.onix.internship.survay.ui.sign_in.register

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.data.local.SurveyDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(
    private val database: SurveyDatabase,
    private val viewLifecycleOwner: LifecycleOwner,
    private val activity: FragmentActivity?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(database, viewLifecycleOwner, activity) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}