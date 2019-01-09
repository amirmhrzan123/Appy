package com.ebpearls.sample.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ebpearls.sample.data.room.dao.UserDao
import com.ebpearls.sample.ui.login.LoginResponse

@Database(entities = [LoginResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}


val MIGRATION_1_2: Migration = object : Migration(0, 1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since we didn't alter the table, there's nothing else to do here.
    }
}
