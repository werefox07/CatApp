package com.werefox.core_data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatFavorite(
    @PrimaryKey
    val id: String,
    val url: String,
    val title: String
)