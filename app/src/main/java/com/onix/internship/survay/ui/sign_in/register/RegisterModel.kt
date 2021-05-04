package com.onix.internship.survay.ui.sign_in.register

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
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

    fun isEmpty() = firstName.isEmpty() || lastName.isEmpty()
            || login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
}
