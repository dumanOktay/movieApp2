package com.duman.movieapp.modelview

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.duman.movieapp.model.Credits
import com.duman.movieapp.model.MovieDetail
import com.duman.movieapp.network.MovieDetailRepository

class MoviesDetailViewModel : ViewModel(), MovieDetailDataSource.GetMovieCallback {
    val movieDetailLiveData = MutableLiveData<MovieDetail>()
    var creditsLiveData = MutableLiveData<Credits>()
    private var movieDetailDataSource = MovieDetailRepository()
    var videoInfoLiveData = MutableLiveData<String>()

    fun getMoviesDetail(movie_id: String) {
        movieDetailDataSource.getMovie(movie_id, this)
    }

    override fun onMovieLoaded(movieDetail: MovieDetail) {
        movieDetailLiveData.value = movieDetail
    }

    override fun onCreditsLoaded(credits: Credits) {
        creditsLiveData.value = credits
    }

    override fun onVideoInfoLoaded(videoUrl: String) {
        videoInfoLiveData.value = videoUrl
    }

}
