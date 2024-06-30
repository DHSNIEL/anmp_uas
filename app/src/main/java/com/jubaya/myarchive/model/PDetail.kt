package com.jubaya.myarchive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "planetdetails",
    foreignKeys = [ForeignKey(
        entity = Planet::class,
        parentColumns = ["id"],
        childColumns = ["planet_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("planet_id")]
)
data class PDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val detail: String,
    @ColumnInfo(name = "planet_id")
    val planetId: Int
)

