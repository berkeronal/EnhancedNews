package com.berker.enhancednews.ui.news.list.extension

import android.widget.ImageView
import com.berker.enhancednews.R
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.news)
        .into(this)
}

fun ImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.news)
        .into(this)
}