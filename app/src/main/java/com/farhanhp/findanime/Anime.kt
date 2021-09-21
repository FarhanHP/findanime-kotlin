package com.farhanhp.findanime

data class Anime(
  val id: Int,
  val title: String,
  val description: String,
  val coverImage: Int,
  val bannerImage: Int,
) {
  fun toFavoriteAnimeEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(id, title, description, bannerImage, coverImage)
  }
}
