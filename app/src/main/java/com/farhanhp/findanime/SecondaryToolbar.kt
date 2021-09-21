package com.farhanhp.findanime

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.withStyledAttributes

class SecondaryToolbar@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): Toolbar(context, attrs, defStyleAttr) {
  private val backButton: Button
  private val toolbarTitle: TextView
  private var parentActivity: AppCompatActivity? = null

  init {
    LayoutInflater.from(context).inflate(R.layout.secondary_toolbar, this, true)
    toolbarTitle = findViewById(R.id.secondaryToolbar_title)
    backButton = findViewById(R.id.secondaryToolbar_backBtn)
    backButton.setOnClickListener{
      onBack()
    }

    context.withStyledAttributes(attrs, R.styleable.SecondaryToolbar) {
      toolbarTitle.text = getString(R.styleable.SecondaryToolbar_toolbarTitle)
    }
  }

  private fun onBack() {
    parentActivity?.onBackPressed()
  }

  fun setActivity(activity: AppCompatActivity) {
    parentActivity = activity
  }

  fun setToolbarTitle(title: String){
    toolbarTitle.text = title
  }
}