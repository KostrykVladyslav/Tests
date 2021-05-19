package com.onix.internship.survay.ui.roles.admin

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.data.local.user.User
import kotlinx.android.parcel.Parcelize

data class AdminModel(
    private var _signedUser: User = User()
): BaseObservable(){

    @Bindable
    var signedUser = _signedUser
    set(value) {
        field = value
        notifyPropertyChanged(BR.signedUser)
    }
}
