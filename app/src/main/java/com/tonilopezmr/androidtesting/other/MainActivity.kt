package com.tonilopezmr.androidtesting.other

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tonilopezmr.androidtesting.R

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_home)
  }
}