package com.onix.internship.survay.ui.sign_in.login

import android.annotation.SuppressLint
import android.content.Context
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.data.user.User
import com.onix.internship.survay.data.user.UserViewModel
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections

class LoginViewModel(
    private val userViewModel: UserViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    @field:SuppressLint("StaticFieldLeak") private val context: Context?
) : ViewModel() {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val model = LoginModel()

    private val _errorLogin = MutableLiveData<Boolean>()
    val errorLogin: LiveData<Boolean> = _errorLogin

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> = _errorPassword

    fun login() {
        model.apply {
            _errorLogin.value = login.isEmpty()
            _errorPassword.value = password.isEmpty()
            if (!isError()) {
                userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                    if (isLoginAndPasswordIsCorrect(user, login, password)) {
                        _navigationLiveEvent.value =
                            SignInFragmentDirections.actionSignInFragmentToMainFragment()
                    } else {
                        Toast
                            .makeText(context, "Data is incorrect!", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }

    private fun isLoginAndPasswordIsCorrect(
        user: List<User>,
        login: String,
        password: String
    ): Boolean {

        for (item in user){
            if (item.login == login && item.password == password){
                return true
            }
        }

        return false
    }
}