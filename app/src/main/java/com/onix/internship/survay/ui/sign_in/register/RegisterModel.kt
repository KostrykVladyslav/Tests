package com.onix.internship.survay.ui.sign_in.register

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.onix.internship.survay.arch.error_states.ErrorStates
import com.onix.internship.survay.data.local.user.User
import com.onix.internship.survay.data.security.md5
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterModel(
    var _firstName: String = "",
    var _lastName: String = "",
    var _login: String = "",
    var _password: String = "",
    var _confirmPassword: String = ""
) : BaseObservable(), Parcelable {

    @IgnoredOnParcel
    @get:Bindable
    var firstName: String = _firstName
        set(value) {
            _firstName = value
            field = value
        }

    @IgnoredOnParcel
    @get:Bindable
    var lastName: String = _lastName
        set(value) {
            _lastName = value
            field = value
        }

    @IgnoredOnParcel
    @get:Bindable
    var login: String = _login
        set(value) {
            _login = value
            field = value
        }

    @IgnoredOnParcel
    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
        }

    @IgnoredOnParcel
    @get:Bindable
    var confirmPassword: String = _confirmPassword
        set(value) {
            _confirmPassword = value
            field = value
        }


    fun isPasswordCorrect(): ErrorStates{
        return when{
            confirmPassword.isEmpty() -> ErrorStates.EMPTY_FIELD

            password != confirmPassword -> ErrorStates.PASSWORD_NOT_THE_SAME

            else -> ErrorStates.NONE
        }
    }

    fun isLastNameEmpty(): ErrorStates {
        return if (lastName.isEmpty())
            ErrorStates.EMPTY_FIELD
        else
            ErrorStates.NONE
    }

    fun isFirstNameEmpty(): ErrorStates {
        return if (firstName.isEmpty())
            ErrorStates.EMPTY_FIELD
        else
            ErrorStates.NONE
    }

    fun isPasswordEmpty(): ErrorStates {
        return if (password.isEmpty())
            ErrorStates.EMPTY_FIELD
        else
            ErrorStates.NONE
    }

    fun isLoginEmpty(): ErrorStates {
        return if (login.isEmpty())
            ErrorStates.EMPTY_FIELD
        else
            ErrorStates.NONE
    }

    fun isError() = firstName.isEmpty()
            || lastName.isEmpty()
            || login.isEmpty()
            || password.isEmpty()
            || confirmPassword.isEmpty()
            || password != confirmPassword

    fun isLoginDuplicate(login: String, list: List<User>): Boolean {
        if (list.isNotEmpty()) {
            for (item in list) {
                if (login == item.login) {
                    return true
                }
            }
        }
        return false
    }


    fun insertUser(user: List<User>): User{
        return User(
            0,
            firstName,
            lastName,
            login,
            md5(password),
            isAdminRole(user)
        )
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
