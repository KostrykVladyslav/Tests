package com.onix.internship.survay.ui.roles.admin.edit_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.user.User

@Suppress("UNCHECKED_CAST")
class EditUserViewModelFactory(private val user: User, private val database: SurveyDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditUserViewModel::class.java)) {
            return EditUserViewModel(user, database) as T
        }
        throw  IllegalArgumentException("Error")
    }
}