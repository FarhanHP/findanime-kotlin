package com.farhanhp.findanime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.farhanhp.findanime.databinding.FragmentAboutPageBinding

class AboutPageFragment : Fragment() {
  private lateinit var binding: FragmentAboutPageBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_page, container, false)
    binding.aboutPageFragmentToolbar.setActivity(activity as AppCompatActivity)

    return binding.root
  }
}