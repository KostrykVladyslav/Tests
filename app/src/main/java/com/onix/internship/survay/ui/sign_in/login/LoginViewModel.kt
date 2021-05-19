package com.onix.internship.survay.ui.sign_in.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.error_states.ErrorStates
import com.onix.internship.survay.arch.events.SingleLiveEvent
import com.onix.internship.survay.data.local.SurveyDatabase
import com.onix.internship.survay.data.local.auth.Auth
import com.onix.internship.survay.data.security.md5
import com.onix.internship.survay.ui.sign_in.SignInFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.logging.Handler

class LoginViewModel(
    private val database: SurveyDatabase,
    @SuppressLint("StaticFieldLeak") activity: FragmentActivity?
) : ViewModel() {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val model = LoginModel()

    private val _errorLogin = MutableLiveData(ErrorStates.NONE)
    val errorLogin: LiveData<ErrorStates> = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorStates.NONE)
    val errorPassword: LiveData<ErrorStates> = _errorPassword

    private val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

    private val delay = 3000L

    init {
        viewModelScope.launch(Dispatchers.IO) {

            Looper.prepare()
            object : Timer() {}.schedule(object : TimerTask() {
                override fun run() {
                    model.login = "vlad"
                }

            }, delay)

        }
    }

    fun login() {
        model.apply {
            _errorLogin.value = isLoginEmpty()
            _errorPassword.value = isPasswordEmpty()

            if (!isError()) {
                navigate()
            }
        }
    }

    private fun navigate() {
        model.apply {
            viewModelScope.launch(Dispatchers.IO) {
                val user = database.userDao.getLoginAndPassword(login, md5(password))
                if (user.isNotEmpty()) {
                    database.authDao.insert(
                        Auth(
                            0,
                            user.first().id,
                            System.currentTimeMillis()
                        )
                    )

                    preferences?.edit()?.apply {
                        putBoolean("is_signed", true)
                        apply()
                    }

                    _navigationLiveEvent.postValue(
                        when (user.first().role) {
                            0 -> SignInFragmentDirections
                                .actionSignInFragmentToAdminFragment()
                            1 -> SignInFragmentDirections
                                .actionSignInFragmentToManagerFragment()
                            else -> SignInFragmentDirections
                                .actionSignInFragmentToUserFragment()
                        }

                    )
                } else _errorPassword.postValue(ErrorStates.INCORRECT_DATA)
            }
        }
    }
}