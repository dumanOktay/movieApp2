package com.duman.movieapp.network

import android.arch.paging.PageKeyedDataSource
import com.duman.movieapp.model.Movie
import com.duman.movieapp.model.MovieResponse
import com.duman.movieapp.network.ApiConst.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository : PageKeyedDataSource<Int, Movie>() {
    lateinit var queryString: String
    private val movieApi = ApiClient.getClient().create(MovieApi::class.java)
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {


        movieApi.getMovies(queryString, API_KEY, 1).enqueue(object : Callback<MovieResponse?> {
            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        callback.onResult(it, 1, 2)
                    }
                } else {
//                    callback.onError()
                }
            }
        })
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        movieApi.getMovies(queryString, API_KEY, params.key).enqueue(object : Callback<MovieResponse?> {
            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        callback.onResult(it, params.key + 1)
                    }
                } else {
//                    callback.onError()
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}