package com.jubaya.myarchive.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE email = :email")
    fun selectCheck(email:String): User

    @Update
    fun updateUser(user:User)

    @Query("UPDATE user SET firstname= :fname, lastname= :lname, password= :pass")
    fun update(fname:String, lname:String, pass:String)

    @Delete
    fun deleteUser(user:User)
}