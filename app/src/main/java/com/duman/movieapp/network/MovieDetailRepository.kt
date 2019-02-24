package com.duman.movieapp.network

import com.duman.movieapp.model.Credits
import com.duman.movieapp.model.MovieDetail
import com.duman.movieapp.model.VideoInfo
import com.duman.movieapp.modelview.MovieDetailDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository : MovieDetailDataSource {
    override fun getMovie(id: String, callback: MovieDetailDataSource.GetMovieCallback) {
        movieApi.getMoviesDetail(id).enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body ?: return
                    callback.onMovieLoaded(body)
                    getCredits(id,callback)
                    getVideoInfo(id,callback)
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


    override fun getCredits(id: String, callback: MovieDetailDataSource.GetMovieCallback) {
        movieApi.getCredits(id).enqueue(object : Callback<Credits> {
            override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                if (response.isSuccessful) {
                    val credits = response.body()
                    credits ?: return
                    callback.onCreditsLoaded(credits)
                }
            }

            override fun onFailure(call: Call<Credits>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun getVideoInfo(id: String, callback: MovieDetailDataSource.GetMovieCallback) {
        movieApi.getVideos(id).enqueue(object : Callback<VideoInfo> {

            override fun onResponse(call: Call<VideoInfo>, response: Response<VideoInfo>) {
                if (response.isSuccessful) {
                    val results = response.body()?.results?:return
                    if (results.isNotEmpty()) {
                        val url = "https://www.youtube.com/watch?v=" + results.get(0).key
                        callback.onVideoInfoLoaded(url)
                    }
                }
            }

            override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private val movieApi = ApiClient.getClient().create(MovieApi::class.java)
}