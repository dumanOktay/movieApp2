package com.duman.movieapp.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.duman.movieapp.model.Movie

class MovieSource(val queryString: String,val movieApi: MovieApi): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
// We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: 1// burda keyi belirledik
        return try {
            val movies = movieApi.getMovies(queryString, ApiConst.API_KEY, position)
            val nextKey = if (movies.results.isEmpty()) null else {
                (position + (params.loadSize / 5)).toInt()
            }
            LoadResult.Page(
                data = movies.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey=nextKey
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}