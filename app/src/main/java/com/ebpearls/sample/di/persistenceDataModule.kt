package com.ebpearls.sample.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ebpearls.sample.data.prefs.PrefsManager
import com.ebpearls.sample.data.prefs.PrefsManagerImpl
import com.ebpearls.sample.data.room.AppDatabase
import com.ebpearls.sample.data.room.MIGRATION_1_2
import com.ebpearls.sample.data.room.dao.UserDao
import com.ebpearls.sample.di.PersistenceDataSourceProperties.DB_NAME
import com.ebpearls.sample.di.PersistenceDataSourceProperties.PREF_NAME
import org.koin.dsl.module.module

/**
 * Remote Web Service dataSource
 */
val persistenceDataModule = module {
    single { provideSharePreference(get(), getProperty(PREF_NAME)) }
    single { providePrefsManager(get()) as PrefsManager }
    single { provideRoomDatabase(get(),getProperty(DB_NAME)) }
    single { provideUserDao(get()) }
}


object PersistenceDataSourceProperties {
    const val PREF_NAME = "PREF_NAME"
    const val DB_NAME = "DB_NAME"
}


fun provideSharePreference(context: Context, prefName: String): SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

fun providePrefsManager(pref: SharedPreferences) = PrefsManagerImpl(pref)

/**
 * TODO Provide the instance of room database
 * @param context
 */
fun provideRoomDatabase(context: Context,dbName:String): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, dbName).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration().build()
}

fun provideUserDao(appDatabase: AppDatabase):UserDao=appDatabase.getUserDao()


