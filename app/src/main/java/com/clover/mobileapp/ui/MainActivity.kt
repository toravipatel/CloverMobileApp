package com.clover.mobileapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.clover.mobileapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolBar()
    }

    /*
    * Function to set up back button in toolbar
    * */
    private fun setUpToolBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /*
    * Function to handle back button with nav graph
    * */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}