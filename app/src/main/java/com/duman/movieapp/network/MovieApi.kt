package com.duman.movieapp.network


import com.duman.movieapp.model.Credits
import com.duman.movieapp.model.MovieDetail
import com.duman.movieapp.model.MovieResponse
import com.duman.movieapp.model.VideoInfo
import com.duman.movieapp.network.ApiConst.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/{type}")
    fun getMovies(@Path("type") type: String, @Query("api_key") apiKey: String, @Query("page") page: Int): Call<MovieResponse>

    @GET("3/search/movie")
    fun getMovies(@Query("api_key") apiKey: String, @Query("page") page: Int,@Query("query") query: String): Call<MovieResponse>


    @GET("3/movie/{movie_id}?api_key=$API_KEY")
    fun getMoviesDetail(@Path("movie_id") movie_id: String): Call<MovieDetail>

    @GET("3/movie/{movie_id}/credits?api_key=$API_KEY")
    fun getCredits(@Path("movie_id") movie_id: String): Call<Credits>

    @GET("3/movie/{id}/videos?api_key=$API_KEY")
    fun getVideos(@Path("id") id: String): Call<VideoInfo>
}
