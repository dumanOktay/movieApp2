package com.duman.movieapp.view.adapters

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.duman.movieapp.R
import com.duman.movieapp.model.Movie
import com.duman.movieapp.utils.loadImage
import com.duman.movieapp.view.adapters.BaseMovieAdapter.MovieViewHolder
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_top_rated.view.*

class BaseMovieAdapter : PagingDataAdapter<Movie, MovieViewHolder>(diffCallback) {

    private var movieClickListener: MovieClickListener? = null


    fun setMovieClickListener(movieClickListener: MovieClickListener) {
        this.movieClickListener = movieClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_top_rated, viewGroup, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, i: Int) {
        getItem(i)?.let { movieViewHolder.bind(it) }
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) = with(itemView) {
            img.loadImage(movie.backdropPath)
            movie_title.text = movie.title

            img.setOnClickListener {
                if (movieClickListener != null) {
                    movieClickListener!!.onMovieClick(movie)
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}
