package com.onix.internship.survay.ui.sign_in.login

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.arch.error_states.ErrorStates
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    var _login: String = "",
    var _password: String = ""
) : BaseObservable(), Parcelable {

    @IgnoredOnParcel
    @get:Bindable
    var login: String = _login
        set(value) {
            _login = value
            field = value
            notifyPropertyChanged(BR.login)
        }

    @IgnoredOnParcel
    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
            notifyPropertyChanged(BR.password)
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

    fun isError() = login.isEmpty() || password.isEmpty()
}
