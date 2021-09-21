package com.farhanhp.findanime

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.farhanhp.findanime.databinding.FragmentDetailPageBinding
import com.google.android.material.button.MaterialButton

class DetailPageFragment : Fragment() {
  private lateinit var binding: FragmentDetailPageBinding
  private lateinit var toolbar: SecondaryToolbar
  private lateinit var bannerImage: ImageView
  private lateinit var coverImage: ImageView
  private lateinit var anime: Anime
  private lateinit var titleTextView: TextView
  private lateinit var descriptionTextView: TextView
  private lateinit var favoriteButton: MaterialButton
  private lateinit var parentActivity: MainActivity
  private lateinit var shareButton: MaterialButton
  private var animeId: Int? = null
  private var isFavoriteAnime = false

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_page, container, false)
    shareButton = binding.detailPageFragmentShareBtn
    coverImage = binding.detailPageFragmentCover
    bannerImage = binding.detailPageFragmentBanner
    titleTextView = binding.detailPageFragmentTitle
    descriptionTextView = binding.detailPageFragmentDescription
    favoriteButton = binding.detailPageFragmentFavoriteBtn
    parentActivity = activity as MainActivity

    animeId = arguments?.getInt("animeId")
    isFavoriteAnime = parentActivity.checkFavoriteAnimeById(animeId as Int)
    anime = AnimeDummies.getAnimeById(animeId as Int) as Anime
    toolbar = binding.detailPageFragmentToolbar
    toolbar.setActivity(activity as AppCompatActivity)
    toolbar.setToolbarTitle(anime.title)
    bannerImage.setImageDrawable(context?.getDrawable(anime.bannerImage))
    coverImage.setImageDrawable(context?.getDrawable(anime.coverImage))
    titleTextView.text = anime.title
    descriptionTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Html.fromHtml(anime.description, Html.FROM_HTML_MODE_COMPACT)
    } else {
      Html.fromHtml(anime.description)
    }
    shareButton.setOnClickListener{
      parentActivity.shareAnime(anime)
    }

    renderFavoriteButton()

    return binding.root
  }

  private fun renderFavoriteButton() {
    if(isFavoriteAnime) {
      favoriteButton.icon = context?.getDrawable(R.drawable.ic_baseline_star_24)
      favoriteButton.text = getString(R.string.remove_favorite)
      favoriteButton.setOnClickListener{
        deleteFavoriteAnime()
      }
    } else {
      favoriteButton.icon = context?.getDrawable(R.drawable.ic_baseline_star_border_24)
      favoriteButton.text = getString(R.string.add_favorite)
      favoriteButton.setOnClickListener{
        addFavoriteAnime()
      }
    }
  }

  private fun deleteFavoriteAnime() {
    parentActivity.deleteFavoriteAnime(anime.toFavoriteAnimeEntity())
    isFavoriteAnime = false
    renderFavoriteButton()
  }

  private fun addFavoriteAnime() {
    parentActivity.addFavoriteAnime(anime.toFavoriteAnimeEntity())
    isFavoriteAnime = true
    renderFavoriteButton()
  }
}