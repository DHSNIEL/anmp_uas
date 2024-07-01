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
            "ALTER TABLE users ADD COLUMN posts INTEGER DEFAULT 0"
        )
    }
}


fun buildDb(context: Context): ArchiveDatabase {
    return Room.databaseBuilder(context.applicationContext,
        ArchiveDatabase::class.java, DB_NAME)
        .createFromAsset("database/myarchives.db")
        .addMigrations(MIGRATION_1_2)
        .build()
}
