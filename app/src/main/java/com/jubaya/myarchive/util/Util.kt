package com.jubaya.myarchive.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jubaya.myarchive.model.ArchiveDatabase

val DB_NAME = "myarchive"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE user ADD COLUMN img_url TEXT DEFAULT 'https://static.vecteezy.com/system/resources/thumbnails/009/292/244/small/default-avatar-icon-of-social-media-user-vector.jpg' not null")
    }
}

fun buildDb (context: Context):ArchiveDatabase{
    //val db = TodoDatabase.buildDatabase(context)
    val db = Room.databaseBuilder(context,
        ArchiveDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}