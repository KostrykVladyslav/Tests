package com.onix.internship.survay.data.local.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * from user_table WHERE login = :login AND password = :password")
    suspend fun getLoginAndPassword(login: String, password: String): List<User>

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():List<User>
}