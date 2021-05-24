package com.onix.internship.survay.data.local.test

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.onix.internship.survay.data.local.test.Test

@Dao
interface TestDao {

    @Insert
    suspend fun insert(test: Test)

    @Query("SELECT * FROM tests ORDER BY testId ASC")
    suspend fun getAllTests(): List<Test>

    @Query("DELETE FROM tests")
    suspend fun clear()
}