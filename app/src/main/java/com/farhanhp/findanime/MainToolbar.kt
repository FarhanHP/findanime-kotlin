package com.farhanhp.findanime

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar

class MainToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): Toolbar(context, attrs, defStyleAttr) {
  private var circleImage: CircleImage

  init {
    LayoutInflater.from(context).inflate(R.layout.main_toolbar, this, true)
    circleImage = findViewById(R.id.mainToolbar__circleImage)
  }

  fun setOnCircleImageClick(callback: (view: View)->Unit) {
    circleImage.setOnClickListener(callback)
  }
}