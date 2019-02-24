package com.duman.movieapp.model

import com.google.gson.annotations.Expose

import java.util.ArrayList

class MovieResponse internal constructor() {
    @Expose
    var results: List<Movie>
        internal set


    init {
        results = mutableListOf()
    }
}
