package com.jubaya.myarchive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "username")
    var username: String?,
    @ColumnInfo(name = "firstname")
    var firstname: String?,
    @ColumnInfo(name = "lastname")
    var lastname: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "password")
    var password: String?,
    @ColumnInfo(name = "img_url")
    var img_url: String?,
    @ColumnInfo(name = "posts")
    var posts: Int?
){

}
