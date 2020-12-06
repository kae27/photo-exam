package com.cp.kpexam.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(url: String,
                           @DrawableRes
                           placeholder: Int = -1) {
//    Glide.with(this)
//        .load(url)
//        .placeholder(placeholder)
//        .into(this)

    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .into(this)

}