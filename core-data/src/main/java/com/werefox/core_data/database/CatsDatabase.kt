package com.werefox.core_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.werefox.core_data.database.entity.CatFavorite

@Database(entities = [CatFavorite::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
