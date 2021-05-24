package com.onix.internship.survay.data.local.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class Auth(
    @PrimaryKey(autoGenerate = true)
    val authId: Int = 0,

    val authUserId: Int = 0,

    val timeStamp: Long = 0L
)