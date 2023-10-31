package com.ivanojok.myfarm.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ivanojok.myfarm.data.model.AuthResponse

@Database([AuthResponse::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}


class DBBuilder(val context: Context) {
    fun initializeDB(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "room-db"
        ).build()
    }
}
