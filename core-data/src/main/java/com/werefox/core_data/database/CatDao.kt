package com.werefox.core_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.werefox.core_data.database.entity.CatFavorite

@Dao
interface CatDao {

    @Insert
    fun saveCatToFavorite(cat: CatFavorite)

    @Query("SELECT * FROM catfavorite")
    fun getCatsFromFavourite(): List<CatFavorite>
}