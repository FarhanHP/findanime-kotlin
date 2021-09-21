package com.farhanhp.findanime

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.farhanhp.findanime.databinding.FragmentMainPageBinding
import com.google.android.flexbox.FlexboxLayout

class MainPageFragment : Fragment() {
  private lateinit var binding: FragmentMainPageBinding
  private lateinit var animeList: FlexboxLayout
  private lateinit var toolbar: MainToolbar
  private lateinit var parentActivity: MainActivity

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    parentActivity = activity as MainActivity
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)
    toolbar = binding.mainPageFragmentMainToolbar
    toolbar.setOnCircleImageClick {
      toAboutPageFragment(it)
    }
    animeList = binding.animeList
    inflateAnimeList()
    return binding.root
  }

  private fun inflateAnimeList() {
    val favoriteAnimeIds = parentActivity.getAllFavoriteAnimes().map{
      it.id
    }

    for(anime in AnimeDummies.animes) {
      val animeCard = AnimeCard(context as Context)
      animeCard.setOnClickListener{
        toDetailPageFragment(it, anime.id)
      }
      val isFavorite = favoriteAnimeIds.contains(anime.id)
      animeCard.setAttribute(anime.title, anime.coverImage, isFavorite)
      animeCard.setOnAddFavoriteListener {
        addFavoriteAnime(anime.toFavoriteAnimeEntity())
      }
      animeCard.setOnRemoveFavoriteListener {
        deleteFavoriteAnime(anime.toFavoriteAnimeEntity())
      }
      animeList.addView(animeCard)
    }
  }

  private fun deleteFavoriteAnime(favoriteAnimeEntity: FavoriteAnimeEntity) {
    parentActivity.deleteFavoriteAnime(favoriteAnimeEntity)
  }

  private fun addFavoriteAnime(favoriteAnimeEntity: FavoriteAnimeEntity) {
    parentActivity.addFavoriteAnime(favoriteAnimeEntity)
  }

  private fun toDetailPageFragment(view: View, animeId: Int){
    val bundle = bundleOf("animeId" to animeId)
    view.findNavController().navigate(R.id.action_mainPageFragment_to_detailPageFragment, bundle)
  }

  private fun toAboutPageFragment(view: View) {
    view.findNavController().navigate(R.id.action_mainPageFragment_to_aboutPageFragment)
  }
}