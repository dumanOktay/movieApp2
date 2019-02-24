package com.duman.movieapp.modelview

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.duman.movieapp.network.MoviesRepository
import com.duman.movieapp.utils.Injection

class ViewModelFactory private constructor(private val moviesRepository: MoviesRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>)= with(modelClass){
     when{
         isAssignableFrom(MoviesViewModel::class.java) -> MoviesViewModel(moviesRepository)
         isAssignableFrom(MoviesDetailViewModel::class.java) -> MoviesDetailViewModel()
         else ->
             throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
     }
    } as T

    companion object {
        @Volatile private var INSTANCE:ViewModelFactory?=null

        fun getInstance()= INSTANCE?: synchronized(ViewModelFactory::class.java){
            INSTANCE?: ViewModelFactory(Injection.provideMoveRepository()).also {
                INSTANCE = it
            }
        }
    }
}