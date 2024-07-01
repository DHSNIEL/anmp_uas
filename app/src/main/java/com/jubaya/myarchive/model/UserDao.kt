package com.jubaya.myarchive.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun selectUser(id: Int): User?

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun check(username: String, password: String): User?

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}