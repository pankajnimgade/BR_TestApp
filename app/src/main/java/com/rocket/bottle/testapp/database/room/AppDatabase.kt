package com.rocket.bottle.testapp.database.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.rocket.bottle.testapp.database.room.dao.StoreDoa
import com.rocket.bottle.testapp.database.room.seed.Store

@Database(entities = [Store::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storeDoa(): StoreDoa

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "bottle_rocket.db"
            ).build()
    }
}