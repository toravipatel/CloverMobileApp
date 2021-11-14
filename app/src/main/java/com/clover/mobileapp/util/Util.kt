package com.clover.mobileapp.util

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.clover.mobileapp.R

/*
* Util class for utility function
* */
class Util {

    /**
    * Function to load image using glide SDK
     * @param context Context
     * @param imageView ImageView
     * @param url Image Loading URL
    * */
    fun loadImageWithGlide(context:Activity?, imageView: ImageView, url:String){
        context?.let { context ->
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.users_placeholder)
                .into(imageView)
        }
    }
}