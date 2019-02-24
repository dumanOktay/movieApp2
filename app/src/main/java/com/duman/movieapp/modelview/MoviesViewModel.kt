package com.duman.movieapp.modelview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.duman.movieapp.model.Movie
import com.duman.movieapp.network.MoviesRepository
import com.duman.movieapp.network.SearchRepository

class MoviesViewModel(var moviesRepository: MoviesRepository) : ViewModel() {

    var searchRepository = SearchRepository(moviesRepository.cachedData)
    private val pageSize = 10


    private val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize * 2)
        .setEnablePlaceholders(false)
        .build()


    var liveData: LiveData<PagedList<Movie>>? = null

    var mQueryString: String? = ""

    fun getItems(queryString: String): LiveData<PagedList<Movie>>? {
        moviesRepository.queryString = queryString
        mQueryString = queryString
        liveData = LivePagedListBuilder<Int, Movie>(object : DataSource.Factory<Int?, Movie?>() {
            override fun create(): DataSource<Int?, Movie?> {
                return moviesRepository
            }
        }, config).setInitialLoadKey(1)
            .build()
        return liveData
    }

    fun filter(str: String, observer: Observer<PagedList<Movie>>) {
        searchRepository.query = str
        liveData?.removeObserver(observer)

        liveData = if (str.length > 2) {
            LivePagedListBuilder<Int, Movie>(object : DataSource.Factory<Int?, Movie?>() {
                override fun create(): DataSource<Int?, Movie?> {
                    return searchRepository
                }
            }, config).build()
        } else {
            mQueryString?.let { getItems(it) }
        }

        liveData?.observeForever(observer)
    }
}


