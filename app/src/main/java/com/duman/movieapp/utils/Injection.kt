package com.duman.movieapp.utils

import com.duman.movieapp.network.MoviesRepository

object Injection {
    fun provideMoveRepository() =MoviesRepository()
}