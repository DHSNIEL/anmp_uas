package com.jubaya.myarchive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
    (
        tableName = "planets",
//    foreignKeys = [ForeignKey(
//        entity = User::class,
//        parentColumns = ["id"],
//        childColumns = ["author_id"],
//        onDelete = ForeignKey.CASCADE
//    )],
//    indices = [Index(value = ["author_id"])]
)
data class Planet(
    @ColumnInfo(name = "name")
    var name:String?,
    @ColumnInfo(name = "summary")
    var summary:String?,
    @ColumnInfo(name = "img_url")
    var img_url:String?,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
