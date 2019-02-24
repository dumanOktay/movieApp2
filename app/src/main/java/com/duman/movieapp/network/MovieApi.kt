package com.duman.movieapp.network


import com.duman.movieapp.model.Credits
import com.duman.movieapp.model.MovieDetail
import com.duman.movieapp.model.MovieResponse
import com.duman.movieapp.model.VideoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

import com.duman.movieapp.network.ApiConst.Companion.API_KEY

interface MovieApi {

    @GET("3/movie/{type}")
    fun getMovies(@Path("type") type: String, @Query("api_key") apiKey: String, @Query("page_id") page: Int): Call<MovieResponse>

    @GET("3/search/movie")
    fun getMovies(@Query("api_key") apiKey: String, @Query("page_id") page: Int,@Query("query") query: String): Call<MovieResponse>


    @GET("3/movie/{movie_id}?api_key=$API_KEY")
    fun getMoviesDetail(@Path("movie_id") movie_id: String): Call<MovieDetail>

    @GET("3/movie/{movie_id}/credits?api_key=$API_KEY")
    fun getCredits(@Path("movie_id") movie_id: String): Call<Credits>

    @GET("3/movie/{id}/videos?api_key=$API_KEY")
    fun getVideos(@Path("id") id: String): Call<VideoInfo>
}
