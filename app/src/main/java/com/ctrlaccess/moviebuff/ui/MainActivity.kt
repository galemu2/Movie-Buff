package com.ctrlaccess.moviebuff.ui

import android.app.Activity
 import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ctrlaccess.moviebuff.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}