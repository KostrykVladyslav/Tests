package com.onix.internship.survay.ui.sign_in.login

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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
        }

    @IgnoredOnParcel
    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
        }

    fun isError() = login.isEmpty() || password.isEmpty()
}
