package com.farhanhp.findanime

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes

class CircleImage @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {

  private var imageView: ImageView

  init {
    LayoutInflater.from(context).inflate(R.layout.circle_image, this, true)
    orientation = VERTICAL
    imageView = findViewById(com.farhanhp.findanime.R.id.circleImage__imageView)

    context.withStyledAttributes(attrs, R.styleable.CircleImage) {
      imageView.background = getDrawable(R.styleable.CircleImage_backgroundColor)
      imageView.setImageDrawable(getDrawable(R.styleable.CircleImage_src))
      imageView.contentDescription = getString(R.styleable.CircleImage_contentDescription)
    }
  }
}
