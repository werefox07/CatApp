package com.werefox.core_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.werefox.core_data.database.entity.CatFavorite
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCatToFavorite(cat: CatFavorite): Completable

    @Query("SELECT * FROM catfavorite")
    fun getCatsFromFavourite(): Single<List<CatFavorite>>
}