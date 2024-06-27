package com.jubaya.myarchive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "PDetail",
    foreignKeys = [ForeignKey(
        entity = Planet::class,
        parentColumns = ["id"],
        childColumns = ["planet_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["planet_id"])]
)
data class PDetail(
    @ColumnInfo(name = "detail")
    var detail:String?,
    @ColumnInfo(name = "planet_id")
    var planet_id:Int,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
