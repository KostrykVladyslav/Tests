package com.onix.internship.survay.data.local.answer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.onix.internship.survay.data.local.answer.Answer

@Dao
interface AnswersDao {

    @Insert
    fun insert(answer: Answer)

    @Update
    fun update(answer: Answer)

    @Query("SELECT * FROM answers WHERE answerQuestionId = :questionId")
    fun getAnswersByQuestion(questionId: Long): List<Answer>

}