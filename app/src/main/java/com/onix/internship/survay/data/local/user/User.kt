package com.onix.internship.survay.data.local.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var login: String = "",
    var password: String = "",
    var role: Int = 0
): Parcelable
