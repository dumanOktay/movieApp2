package com.duman.movieapp.modelview

import com.duman.movieapp.model.Credits
import com.duman.movieapp.model.MovieDetail

interface MovieDetailDataSource {
    interface GetMovieCallback {
        fun onMovieLoaded(movieDetail: MovieDetail)

        fun onCreditsLoaded(credits: Credits)

        fun onVideoInfoLoaded(videoUrl: String)
    }

    fun getMovie(id: String, callback: GetMovieCallback)

    fun getCredits(id: String, callback: GetMovieCallback)

    fun getVideoInfo(id: String, callback: GetMovieCallback)
}