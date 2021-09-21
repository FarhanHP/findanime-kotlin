package com.farhanhp.findanime

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteAnimeEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
  abstract fun favoriteAnimeEntityDao(): FavoriteAnimeEntityDao
}