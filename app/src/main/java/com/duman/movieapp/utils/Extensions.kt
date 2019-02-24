package com.duman.movieapp.utils

import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

private const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun ImageView.loadImage(str: String?) {
    str?:return
    Picasso.get().load(IMG_BASE_URL + str).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(this)
}