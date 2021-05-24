package com.onix.internship.survay.ui.roles.admin.edit_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.error_states.ErrorStates
import com.onix.internship.survay.arch.events.SingleLiveEvent
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserViewModel(val user: User, private val database: SurveyDatabase) : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _errorLogin = MutableLiveData(ErrorStates.NONE)
    val errorLogin: LiveData<ErrorStates> = _errorLogin

    private val _errorFirstName = MutableLiveData(ErrorStates.NONE)
    val errorFirstName: LiveData<ErrorStates> = _errorFirstName

    private val _errorLastName = MutableLiveData(ErrorStates.NONE)
    val errorLastName: LiveData<ErrorStates> = _errorLastName

    fun onSave(){
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao.updateUser(user)
            _navigationEvent.postValue(EditUserFragmentDirections
                .actionEditUserFragmentToAdminFragment())
        }
    }

    fun isSelected(roleIndex: Int) {
        user.role = roleIndex
    }
}