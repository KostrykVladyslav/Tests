package com.onix.internship.survay.data.local.result

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val resultId: Int,

    val resultUserId: Int,

    val date: Long = Calendar.getInstance().timeInMillis,

    val resultTestId: Int,

    val score: Int
)