package com.onix.internship.survay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onix.internship.survay.data.local.access.Access
import com.onix.internship.survay.data.local.access.AccessDao
import com.onix.internship.survay.data.local.answer.Answer
import com.onix.internship.survay.data.local.answer.AnswersDao
import com.onix.internship.survay.data.local.auth.Auth
import com.onix.internship.survay.data.local.auth.AuthDao
import com.onix.internship.survay.data.local.question.Question
import com.onix.internship.survay.data.local.question.QuestionsDao
import com.onix.internship.survay.data.local.result.Result
import com.onix.internship.survay.data.local.result.ResultDao
import com.onix.internship.survay.data.local.test.Test
import com.onix.internship.survay.data.local.test.TestDao
import com.onix.internship.survay.data.local.user.User
import com.onix.internship.survay.data.local.user.UserDao

@Database(entities = [User::class, Test::class, Result::class, Question::class, Answer::class, Access::class, Auth::class], version = 1, exportSchema = false)
abstract class SurveyDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val testDao: TestDao
    abstract val resultDao: ResultDao
    abstract val questionsDao: QuestionsDao
    abstract val answerDao: AnswersDao
    abstract val accessDao: AccessDao
    abstract val authDao: AuthDao


    companion object {

        @Volatile
        private var INSTANCE: SurveyDatabase? = null

        fun getDatabase(context: Context): SurveyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SurveyDatabase::class.java,
                    "survey_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}