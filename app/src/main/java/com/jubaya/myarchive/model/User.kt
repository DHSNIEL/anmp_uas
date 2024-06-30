package com.jubaya.myarchive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "username")
    var username:String?,
    @ColumnInfo(name = "firstname")
    var firstname:String?,
    @ColumnInfo(name = "lastname")
    var lastname:String?,
    @ColumnInfo(name = "email")
    var email:String?,
    @ColumnInfo(name = "password")
    var password:String?,
    @ColumnInfo(name = "img_url")
    var img_url:String?

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
