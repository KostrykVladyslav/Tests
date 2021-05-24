package com.onix.internship.survay.ui.roles.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.data.local.SurveyDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AdminViewModelFactory(private val database: SurveyDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(database) as T
        }
        throw  IllegalArgumentException("Error")
    }
}