package com.farhanhp.findanime

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteAnimeEntityDao {
  @Query("SELECT * FROM favoriteanimeentity")
  fun getAll(): List<FavoriteAnimeEntity>

  @Query("SELECT * FROM favoriteanimeentity WHERE id IN (:userIds)")
  fun loadAllByIds(userIds: IntArray): List<FavoriteAnimeEntity>

  @Insert
  fun insert(vararg favoriteAnime: FavoriteAnimeEntity)

  @Delete
  fun delete(favoriteAnime: FavoriteAnimeEntity)
}