package com.onix.internship.survay.ui.sign_in.register

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.error_states.ErrorStates
import com.onix.internship.survay.arch.events.SingleLiveEvent
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.auth.Auth
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val database: SurveyDatabase,
    private val viewLifecycleOwner: LifecycleOwner,
    activity: FragmentActivity?
) :
    ViewModel() {
    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val model = RegisterModel()

    private val _errorFirstName = MutableLiveData(ErrorStates.NONE)
    val errorFirstName: LiveData<ErrorStates> = _errorFirstName

    private val _errorLastName = MutableLiveData(ErrorStates.NONE)
    val errorLastName: LiveData<ErrorStates> = _errorLastName

    private val _errorLogin = MutableLiveData(ErrorStates.NONE)
    val errorLogin: LiveData<ErrorStates> = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorStates.NONE)
    val errorPassword: LiveData<ErrorStates> = _errorPassword

    private val _errorPasswordConfirm = MutableLiveData(ErrorStates.NONE)
    val errorPasswordConfirm: LiveData<ErrorStates> = _errorPasswordConfirm

    private val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

    fun register() {
        model.apply {
            _errorFirstName.value = isFirstNameEmpty()
            _errorLastName.value = isLastNameEmpty()
            _errorLogin.value = isLoginEmpty()
            _errorPassword.value = isPasswordEmpty()
            _errorPasswordConfirm.value = isPasswordCorrect()

            if (!isError()) {
                navigate()
            }
        }
    }

    private fun navigate() {
        model.apply {
            viewModelScope.launch(Dispatchers.IO) {
                val userList = database.userDao.readAllData()
                if (!isLoginDuplicate(login, userList)) {
                    val user = insertUser(userList)
                    val userId = database.userDao.insert(user)
                    database.authDao.insert(
                        Auth(
                            authUserId = userId.toInt(),
                            timeStamp = System.currentTimeMillis()
                        )
                    )

                    preferences?.edit()?.apply {
                        putBoolean("is_signed", true)
                        apply()
                    }

                    _navigationLiveEvent.postValue(
                        when {
                            userList.isEmpty() -> SignInFragmentDirections.actionSignInFragmentToAdminFragment()
                            else -> SignInFragmentDirections.actionSignInFragmentToUserFragment()
                        }
                    )
                } else _errorLogin.postValue(ErrorStates.LOGIN_HAS_USED)
            }
        }
    }
}