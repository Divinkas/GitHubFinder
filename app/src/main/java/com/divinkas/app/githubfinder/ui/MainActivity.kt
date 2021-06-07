package com.divinkas.app.githubfinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.divinkas.app.githubfinder.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("commit 2_1")
    }
}