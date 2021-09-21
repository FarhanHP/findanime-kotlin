package com.farhanhp.findanime

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import com.google.android.material.button.MaterialButton

class AnimeCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {
  private val imageView: ImageView
  private val textView: TextView
  private val favoriteBtn: MaterialButton
  private lateinit var onRemoveFavorite: (() -> Unit)
  private lateinit var onAddFavorite: (() -> Unit)
  private var isFavorite: Boolean = false

  init {
    LayoutInflater.from(context).inflate(R.layout.anime_card, this, true)

    favoriteBtn = findViewById(R.id.animeCard_favoriteBtn)
    imageView = findViewById(R.id.animeCard_imageView)
    textView = findViewById(R.id.animeCard_title)

    context.withStyledAttributes(attrs, R.styleable.AnimeCard) {
      imageView.setImageDrawable(getDrawable(R.styleable.AnimeCard_animeImage))
      textView.text = getString(R.styleable.AnimeCard_animeTitle)
    }
  }

  fun setAttribute(title: String?, image: Int, isFavorite: Boolean) {
    this.isFavorite = isFavorite
    renderFavoriteButton()
    imageView.setImageDrawable(resources.getDrawable(image))
    textView.text = title
  }

  private fun renderFavoriteButton() {
    if(isFavorite) {
      favoriteBtn.icon = context.getDrawable(R.drawable.ic_baseline_star_48)
      favoriteBtn.setOnClickListener{
        onRemoveFavorite()
        isFavorite = false
        renderFavoriteButton()
      }
    } else {
      favoriteBtn.icon = context.getDrawable(R.drawable.ic_baseline_star_border_48)
      favoriteBtn.setOnClickListener{
        onAddFavorite()
        isFavorite = true
        renderFavoriteButton()
      }
    }
  }

  fun setOnAddFavoriteListener(fn: () -> Unit) {
    onAddFavorite = fn
  }

  fun setOnRemoveFavoriteListener(fn: () -> Unit) {
    onRemoveFavorite = fn
  }
}