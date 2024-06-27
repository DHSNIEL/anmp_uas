package com.jubaya.myarchive.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jubaya.myarchive.model.UserDatabase

val DB_NAME = "myarchive"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE user ADD COLUMN img_url")
    }
}

fun buildDb (context: Context):UserDatabase{
    //val db = TodoDatabase.buildDatabase(context)
    val db = Room.databaseBuilder(context,
        UserDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}