package com.onix.internship.survay.data.local.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tests")
data class Test(

    @PrimaryKey(autoGenerate = true)
    val testId: Int,

    val name: String,

    val description: String
)