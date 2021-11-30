package com.duman.movieapp.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.duman.movieapp.model.Movie
import com.duman.movieapp.network.MoviesRepository
import com.duman.movieapp.network.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class MoviesViewModel(var moviesRepository: MoviesRepository) : ViewModel() {

    var searchRepository = SearchRepository()
    private val pageSize = 10


    private val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize * 2)
        .setEnablePlaceholders(false)
        .build()


    var liveData: LiveData<PagedList<Movie>>? = null

    var mQueryString: String? = ""

    fun getItems(queryString: String): Flow<PagingData<Movie>> {

       return moviesRepository.getMovies(queryString).map {
           it.map {
               println("şşş"+ it.title)
               it
           }
       }.cachedIn(viewModelScope)

    }

    fun filter(str: String, observer: Observer<PagedList<Movie>>) {
        searchRepository.query = str
        liveData?.removeObserver(observer)


        liveData?.observeForever(observer)
    }
}


