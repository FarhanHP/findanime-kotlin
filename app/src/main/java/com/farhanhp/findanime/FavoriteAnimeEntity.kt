package com.farhanhp.findanime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteAnimeEntity(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "description") val description: String,
  @ColumnInfo(name= "bannerImage") val bannerImage: Int,
  @ColumnInfo(name= "coverImage") val coverImage: Int,
)
