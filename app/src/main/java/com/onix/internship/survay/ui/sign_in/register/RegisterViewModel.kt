package com.onix.internship.survay.ui.sign_in.register

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.data.user.User
import com.onix.internship.survay.data.user.UserViewModel
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections

class RegisterViewModel(
    private val userViewModel: UserViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    activity: FragmentActivity?
) :
    ViewModel() {
    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val model = RegisterModel()

    private val  preferences = activity?.getPreferences(Context.MODE_PRIVATE)
    private val editor = preferences?.edit()

    private val _errorFirstName = MutableLiveData<Boolean>()
    val errorFirstName: LiveData<Boolean> = _errorFirstName

    private val _errorLastName = MutableLiveData<Boolean>()
    val errorLastName: LiveData<Boolean> = _errorLastName

    private val _errorLogin = MutableLiveData<Boolean>()
    val errorLogin: LiveData<Boolean> = _errorLogin

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> = _errorPassword

    private val _errorPasswordConfirm = MutableLiveData<Boolean>()
    val errorPasswordConfirm: LiveData<Boolean> = _errorPasswordConfirm

    private val _incorrectPassword = MutableLiveData<Boolean>()
    val incorrectPassword: LiveData<Boolean> = _incorrectPassword

    private val _isLoginUsed = MutableLiveData<Boolean>()
    val isLoginUsed: LiveData<Boolean> = _isLoginUsed

    fun register() {
        model.apply {
            _errorFirstName.value = firstName.isEmpty()
            _errorLastName.value = lastName.isEmpty()
            _errorLogin.value = login.isEmpty()
            _errorPassword.value = password.isEmpty()
            _errorPasswordConfirm.value = confirmPassword.isEmpty()
            _incorrectPassword.value = errorPasswordConfirm()

            if (!isError()) {
                userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                    _isLoginUsed.value = isLoginDuplicate(login, user)

                    if (!isLoginDuplicate(login, user)) {
                        userViewModel.addUser(
                            User(
                                0,
                                firstName,
                                lastName,
                                login,
                                password,
                                isAdminRole(user)
                            )
                        )

                        editor?.putString("login", login)
                        editor?.putBoolean("is_signed", true)
                        editor?.apply()

                        _navigationLiveEvent.value =
                            SignInFragmentDirections.actionSignInFragmentToMainFragment()
                    }
                })
            }
        }
    }

    private fun isAdminRole(list: List<User>): Int {
        return when {
            list.isEmpty() -> {
                0
            }
            else -> {
                2
            }
        }
    }
}