package com.onix.internship.survay.data.local.question

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val questionId: Int,

    val questionTestId: Int,

    val type: String,

    val text: String
)