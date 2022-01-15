package com.test.openweather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.openweather.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteRoomDatabase :RoomDatabase() {
    abstract fun favDao(): FavoriteDao
    companion object{
        @Volatile
        private var INSTANCE : FavoriteRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteRoomDatabase::class.java, "favorite")
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }
}