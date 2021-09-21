package com.farhanhp.findanime

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.farhanhp.findanime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var db: AppDatabase
  private lateinit var favoriteAnimeEntityDao: FavoriteAnimeEntityDao

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "findanime").allowMainThreadQueries().build()
    favoriteAnimeEntityDao = db.favoriteAnimeEntityDao()
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  }

  fun getAllFavoriteAnimes(): List<FavoriteAnimeEntity> {
    return favoriteAnimeEntityDao.getAll()
  }

  fun addFavoriteAnime(newFavoriteAnime: FavoriteAnimeEntity) {
    favoriteAnimeEntityDao.insert(newFavoriteAnime)
    Toast.makeText(applicationContext, "Favorite Anime Added !", Toast.LENGTH_SHORT).show()
  }

  fun checkFavoriteAnimeById(id: Int): Boolean {
    return favoriteAnimeEntityDao.loadAllByIds(intArrayOf(id)).isNotEmpty()
  }

  fun deleteFavoriteAnime(favoriteAnime: FavoriteAnimeEntity) {
    favoriteAnimeEntityDao.delete(favoriteAnime)
    Toast.makeText(applicationContext, "Favorite Anime Deleted !", Toast.LENGTH_SHORT).show()
  }

  fun openGithub(view: View) {
    openBrowser(getString(R.string.github_url))
  }

  fun openLinkedIn(view: View) {
    openBrowser(getString(R.string.linkedin_url))
  }

  private fun openBrowser(url: String) {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.addCategory(Intent.CATEGORY_BROWSABLE);
    intent.data = Uri.parse(url)
    startActivity(intent);
  }

  fun shareAnime(anime: Anime) {
    val sendIntent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, "find anime like ${anime.title} in FindAnime App")
      type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Anime")
    startActivity(shareIntent)
  }
}