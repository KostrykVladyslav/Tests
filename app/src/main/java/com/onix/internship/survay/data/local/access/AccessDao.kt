package com.onix.internship.survay.data.local.access

import androidx.room.*
import com.onix.internship.survay.data.local.access.Access

@Dao
interface AccessDao {

    @Insert
    suspend fun insert(access: Access)

    @Update
    suspend fun update(access: Access)

    @Query("SELECT * FROM access WHERE accessUserId = :userId AND accessTestId = :testId")
    suspend fun getAccess(userId: Long, testId: Long): Access?
}
