package com.onix.internship.survay.data.local.answer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = true)
    val answerId: Int,

    val answerQuestionId: Int,

    val score: Int,

    val text: String
)