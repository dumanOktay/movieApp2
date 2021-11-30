package com.duman.movieapp.network

import androidx.paging.*
import com.duman.movieapp.model.Movie
import com.duman.movieapp.model.MovieResponse
import com.duman.movieapp.network.ApiConst.Companion.API_KEY
import kotlinx.coroutines.flow.Flow

class MoviesRepository  {
    private val movieApi = ApiClient.getClient().create(MovieApi::class.java)
    fun getMovies(query:String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = { MovieSource(query,movieApi) }
        ).flow
    }
    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}