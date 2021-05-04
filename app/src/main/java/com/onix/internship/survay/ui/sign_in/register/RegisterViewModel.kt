package com.onix.internship.survay.ui.sign_in.register

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.data.user.User
import com.onix.internship.survay.data.user.UserViewModel
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections

class RegisterViewModel(
    private val userViewModel: UserViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    @field:SuppressLint("StaticFieldLeak") private val context: Context?
) :
    ViewModel() {
    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

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

    val model = RegisterModel()

    fun register() {
        model.apply {
            _errorFirstName.value = firstName.isEmpty()
            _errorLastName.value = lastName.isEmpty()
            _errorLogin.value = login.isEmpty()
            _errorPassword.value = password.isEmpty()
            _errorPasswordConfirm.value = confirmPassword.isEmpty()

            if (!isEmpty()) {
                if (password == confirmPassword) {
                    userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
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
                            _navigationLiveEvent.value =
                                SignInFragmentDirections.actionSignInFragmentToMainFragment()
                        } else {
                            Toast
                                .makeText(context,
                                    "This login has been used!",
                                    Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                } else {
                    Toast
                        .makeText(context,
                            "Password of password confirmation is incorrect",
                            Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun isLoginDuplicate(login: String, list: List<User>): Boolean {
        for (item in list) {
            if (login == item.login) {
                return true
            }
        }
        return false
    }

    private fun isAdminRole(list: List<User>): Int {
        return when {
            list.isEmpty() -> {
                0
            }
            else -> {
                3
            }
        }
    }
}