package com.onix.internship.survay.data.local.auth

import androidx.room.Embedded
import androidx.room.Relation
import com.onix.internship.survay.data.local.user.User

data class AuthAndUser(
    @Embedded val auth: Auth,
    @Relation(
        parentColumn = "authUserId",
        entityColumn = "id"
    )
    val user: User
)
