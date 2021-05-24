package com.onix.internship.survay.ui.sign_in.login

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.data.user.User
import com.onix.internship.survay.data.user.UserViewModel
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections

class LoginViewModel(
    private val userViewModel: UserViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    activity: FragmentActivity?,
) : ViewModel() {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val model = LoginModel()

    private val _errorLogin = MutableLiveData<Boolean>()
    val errorLogin: LiveData<Boolean> = _errorLogin

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> = _errorPassword

    private val _incorrectData = MutableLiveData<Boolean>()
    val incorrectData: LiveData<Boolean> = _incorrectData

    private val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
    private val editor = preferences?.edit()

    fun login() {
        model.apply {
            _errorLogin.value = login.isEmpty()
            _errorPassword.value = password.isEmpty()

            if (!isError()) {
                userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                    _incorrectData.value = (!isLoginAndPasswordCorrect(user, login, password))

                    if (isLoginAndPasswordCorrect(user, login, password)) {
                        _navigationLiveEvent.value =
                            SignInFragmentDirections.actionSignInFragmentToMainFragment()

                        editor?.putBoolean("is_signed", true)
                        editor?.putString("login", login)
                        editor?.apply()
                    }
                })
            }
        }
    }

    private fun isLoginAndPasswordCorrect(
        user: List<User>,
        login: String,
        password: String
    ): Boolean {

        for (item in user) {
            if (item.login == login && item.password == password) {
                return true
            }
        }
        return false
    }
}