package com.onix.internship.survay.ui.roles.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.events.SingleLiveEvent
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewModel(private val database: SurveyDatabase) : ViewModel() {

    private val model = AdminModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            model.signedUser = database.authDao.getCurrentUser().user
            _userList.postValue(database.userDao.readAllData())
        }
    }
}