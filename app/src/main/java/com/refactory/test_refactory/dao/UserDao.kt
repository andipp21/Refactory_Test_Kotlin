package com.refactory.test_refactory.dao

import androidx.room.*
import com.refactory.test_refactory.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE email = :eLog AND password = :pLog")
    fun login(eLog: String, pLog: String): User

    @Query("SELECT * FROM User WHERE email = :eRes")
    fun checkUser(eRes: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user: User): Long

    @Delete
    fun delete(user: User): Int

    @Update
    fun update(user: User): Int
}